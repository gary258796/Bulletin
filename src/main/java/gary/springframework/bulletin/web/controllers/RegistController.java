package gary.springframework.bulletin.web.controllers;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.data.entity.VerificationToken;
import gary.springframework.bulletin.data.model.dto.UserRegistDto;
import gary.springframework.bulletin.data.model.response.GenericResponse;
import gary.springframework.bulletin.normalstuff.event.OnRegistrationSendMailEvent;
import gary.springframework.bulletin.normalstuff.exception.UserAlreadyExistException;
import gary.springframework.bulletin.normalstuff.util.SendEmailHelper;
import gary.springframework.bulletin.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

/**
 * 負責註冊相關的處理(顯示註冊頁面,處理註冊以及註冊認證)
 */
@Controller
@RequestMapping("/regist")
public class RegistController {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private MessageSource messageSource;

    private final UserService userService;
    private final SendEmailHelper mailService;

    public RegistController(UserService userService, SendEmailHelper mailService) { super();
        this.userService = userService;
        this.mailService = mailService;
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
                                           final HttpServletRequest request, final Errors errors) {

        GenericResponse genericResponse = new GenericResponse("successful");

        try {
            final User registeredUser = userService.registerNewUserAccount(userRegistDto);
            String appUrl = getAppUrl(request);
            Locale locale = LocaleContextHolder.getLocale();

            // 發佈事件
            applicationEventPublisher.publishEvent( new OnRegistrationSendMailEvent(registeredUser, appUrl, locale) );

        } catch ( UserAlreadyExistException userAlreadyExistException){
            genericResponse = new GenericResponse("fail", userAlreadyExistException.getMessage() );
        }

        return genericResponse;
    }

    /**
     * 使用者點選驗證信的連結的時候, 由此controller處理
     * @param token
     * @param model
     * @param
     * @return
     */
    @GetMapping(value = "/registrationConfirm")
    public String confirmRegistration(@RequestParam("token") String token, Model model) {

        Locale locale = LocaleContextHolder.getLocale();
        VerificationToken verificationToken = userService.getVerificationToken(token);

        // can't find token in db
        if( verificationToken == null ) {
            // show invalid token page
            String message = messageSource.getMessage("message.register.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "/regist/invalidToken";
        }

        User user = verificationToken.getUser();
        Calendar currentTime = Calendar.getInstance();

        // if token expired

//        (verificationToken.getExpiryDate().getTime() - currentTime.getTime().getTime() ) <= 0
        if( true ) {
            // show invalid token page
            String message = messageSource.getMessage("message.register.tokenExpired", null, locale);
            model.addAttribute("message", message);
            model.addAttribute("expired", true);
            model.addAttribute("token", token);
            return "/regist/invalidToken";
        }

        // all ok , enabled user
        user.setEnabled(true);
        // save to db
        userService.save(user);
        //
        return "login/login";
    }


    /**
     * 重新發送驗證信
     * @return GenericResponse
     */
    @ResponseBody
    @GetMapping(value = "/resendRegistrationEmail")
    public GenericResponse resendEmail(@RequestParam("token") String existingTokenString, HttpServletRequest request) {
        // 重設token以及其過期時間
        final VerificationToken newToken = userService.generateNewVerificationToken(existingTokenString);
        final User user = userService.findUserByToken( newToken.getToken() );
        Locale locale = LocaleContextHolder.getLocale();

        // 重新發送一封信給User
        mailService.resendRegistrationEmail(user.getEmail(), getAppUrl(request), newToken.getToken(), locale);

        String responseMsg = messageSource.getMessage("message.success.resend", null, locale);
        return new GenericResponse(responseMsg);
    }

    /**
     * 導到錯誤憑證頁面
     * @return
     */
    @GetMapping(value = "/invalidToken")
    public String invalidToken() {
        return "/regist/invalidToken";
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
