package gary.springframework.bulletin.web.controllers;

import gary.springframework.bulletin.data.entity.ResetPasswordToken;
import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.data.model.response.GenericResponse;
import gary.springframework.bulletin.normalstuff.event.OnSendMailEvent;
import gary.springframework.bulletin.normalstuff.util.Mail.SendType;
import gary.springframework.bulletin.web.services.ResetPasswordTokenService;
import gary.springframework.bulletin.web.services.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class ForgetPasswordController {

    private ApplicationEventPublisher applicationEventPublisher;
    private MessageSource messageSource;
    private final UserService userService;
    private final ResetPasswordTokenService resetPasswordTokenService;

    public ForgetPasswordController(ApplicationEventPublisher applicationEventPublisher, UserService userService, MessageSource messageSource, ResetPasswordTokenService resetPasswordTokenService) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.userService = userService;
        this.messageSource = messageSource;
        this.resetPasswordTokenService = resetPasswordTokenService;
    }

    /**
     * 進入忘記密碼頁面
     * @return String of page
     */
    @GetMapping("/forgetPassword")
    public String goForgetPasswordPage() {
        return "login/forgotPassword";
    }

    /**
     * 驗證信箱 並且發送重設密碼連結給User
     * @param email
     * @return
     */
    @ResponseBody
    @PostMapping("/forgetPassword")
    public GenericResponse forgetPassword(@RequestParam("email") String email, HttpServletRequest request, Locale locale) {

        // check if the email account is belongs to a registered account
        User user = userService.findByEmail( email );
        if( user == null ){
            return new GenericResponse("fail", messageSource.getMessage("message.emailNotFound", null, locale));
        }

        // Reset token of this user
        ResetPasswordToken pwdToken = resetPasswordTokenService.createPasswordResetTokenForUser(user);

        // Send a token we generated with the email
        applicationEventPublisher.publishEvent( new OnSendMailEvent(getAppUrl(request), locale, user, SendType.reset, pwdToken.getToken() ) );


        return new GenericResponse("successful", messageSource.getMessage("message.resetPasswordEmail.send", null, locale));
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
