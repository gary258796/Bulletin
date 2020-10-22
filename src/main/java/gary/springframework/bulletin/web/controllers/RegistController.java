package gary.springframework.bulletin.web.controllers;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.data.entity.VerificationToken;
import gary.springframework.bulletin.data.model.dto.UserRegistDto;
import gary.springframework.bulletin.data.model.response.GenericResponse;
import gary.springframework.bulletin.normalstuff.event.OnRegistrationCompleteEvent;
import gary.springframework.bulletin.normalstuff.exception.UserAlreadyExistException;
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
public class RegistController {

    private final UserService userService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private MessageSource messageSource;

    public RegistController(UserService userService) { super();
        this.userService = userService;
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
            applicationEventPublisher.publishEvent( new OnRegistrationCompleteEvent(registeredUser, appUrl, locale) );

        } catch ( UserAlreadyExistException userAlreadyExistException){
            genericResponse = new GenericResponse("fail", userAlreadyExistException.getMessage() );
        }

        return genericResponse;
    }

    /**
     * 使用者點選驗證信的連結的時候, 由此controller處理
     * @param token
     * @param model
     * @param request
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
            return "redirect:/invalidToken.html?lang=" + locale.getLanguage();
        }

        User user = verificationToken.getUser();
        Calendar currentTime = Calendar.getInstance();

        // if token expired
        if( (verificationToken.getExpiryDate().getTime() - currentTime.getTime().getTime() ) <= 0 ) {
            // show invalid token page
            String message = messageSource.getMessage("message.register.tokenExpired", null, locale);
            model.addAttribute("message", message);
            return "redirect:/invalidToken.html?lang=" + locale.getLanguage();
        }

        // all ok , enabled user
        user.setEnabled(true);
        // save to db
        userService.save(user);
        return "redirect:/login?lang=" + locale.getLanguage();
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
