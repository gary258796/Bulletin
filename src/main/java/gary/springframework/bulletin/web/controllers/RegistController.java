package gary.springframework.bulletin.web.controllers;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.data.entity.token.VerificationToken;
import gary.springframework.bulletin.data.model.dto.UserRegistDto;
import gary.springframework.bulletin.data.model.response.GenericResponse;
import gary.springframework.bulletin.normalstuff.event.OnSendMailEvent;
import gary.springframework.bulletin.normalstuff.exception.UserAlreadyExistException;
import gary.springframework.bulletin.normalstuff.util.Mail.SendType;
import gary.springframework.bulletin.web.services.UserService;
import gary.springframework.bulletin.web.services.VerificationTokenService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

/**
 * 負責註冊相關的處理(顯示註冊頁面,處理註冊以及註冊認證)
 */
@Controller
@RequestMapping("/regist")
public class RegistController {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final MessageSource messageSource;
    private final UserService userService;
    private final VerificationTokenService verificationTokenService;

    public RegistController(ApplicationEventPublisher applicationEventPublisher, MessageSource messageSource, UserService userService, VerificationTokenService verificationTokenService) {
        super();
        this.applicationEventPublisher = applicationEventPublisher;
        this.messageSource = messageSource;
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
    }

    /**
     * 進入到註冊頁面用
     * @return
     */
    @GetMapping(value = "/regist")
    public String showRegisterPage() {
        return "regist/regist";
    }

    /**
     * 註冊流程
     * 通不過Java校驗api的欄位會透過HandleValidationExceptions class裡面去抓取所有controller丟出但沒去處理之exceptions
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/regist")
    public GenericResponse doRegistration(@Valid @RequestBody final UserRegistDto userRegistDto,
                                           final HttpServletRequest request, final Errors errors, Locale locale) {

        try {
            // Create user and save
            final User registeredUser = userService.registerNewUserAccount(userRegistDto);

            // Create token and save
            final String token = UUID.randomUUID().toString();
            verificationTokenService.createVerificationTokenForUser(registeredUser, token);

            // Publish event
            applicationEventPublisher.publishEvent( new OnSendMailEvent(getAppUrl(request), locale, registeredUser, SendType.send, token) );

        } catch ( UserAlreadyExistException userAlreadyExistException){
            return new GenericResponse("fail", userAlreadyExistException.getMessage() );
        }

        return new GenericResponse("successful");
    }

    /**
     * 使用者點選驗證信的連結的時候, 由此controller處理
     * @param token
     * @param model
     * @param
     * @return
     */
    @GetMapping(value = "/registrationConfirm")
    public String confirmRegistration(@RequestParam("token") String token, Model model, Locale locale) {
        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);

        // can't find token in db
        if( verificationToken == null ) {
            // show invalid token page
            String message = messageSource.getMessage("message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "/regist/invalidToken";
        }

        // get User of verification token with token's id
        User user = userService.findById(verificationToken.getId());
        Calendar currentTime = Calendar.getInstance();

        // if token expired
        if( (verificationToken.getExpiryDate().getTime() - currentTime.getTime().getTime() ) <= 0 ) {
            // show invalid token page
            String message = messageSource.getMessage("message.tokenExpired", null, locale);
            model.addAttribute("message", message);
            model.addAttribute("expired", true);
            model.addAttribute("token", token);
            return "/regist/invalidToken";
        }

        // all ok , enabled user
        user.setUserEnabled(true);
        // save to db
        userService.save(user);
        // TODO: delete this user's token in db
        return "login/login";
    }


    /**
     * 重新發送驗證信
     * @return GenericResponse
     */
    @ResponseBody
    @GetMapping(value = "/resendRegistrationEmail")
    public GenericResponse resendEmail(@RequestParam("token") String existingTokenString, HttpServletRequest request, Locale locale) {
        // 重設token以及其過期時間
        final VerificationToken newToken = verificationTokenService.generateNewVerificationToken(existingTokenString);
        final User user = userService.findUserByToken( newToken.getToken() );

        // 發佈事件
        applicationEventPublisher.publishEvent( new OnSendMailEvent(getAppUrl(request), locale, user, SendType.resend, newToken.getToken()) );

        String responseMsg = messageSource.getMessage("message.success.resend", null, locale);
        return new GenericResponse(responseMsg);
    }

    /* Methods Below */
    /**
     * 取得路徑
     * @param request
     * @return
     */
    private String getAppUrl( HttpServletRequest request ) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
