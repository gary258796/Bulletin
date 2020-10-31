package gary.springframework.bulletin.web.controllers;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.data.model.dto.PasswordDto;
import gary.springframework.bulletin.data.model.response.GenericResponse;
import gary.springframework.bulletin.web.services.ResetPasswordTokenService;
import gary.springframework.bulletin.web.services.UserService;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class ResetPasswordController {

    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;
    private final UserService userService;
    private final ResetPasswordTokenService resetPasswordTokenService;

    public ResetPasswordController(PasswordEncoder passwordEncoder, MessageSource messageSource,
                                   UserService userService, ResetPasswordTokenService resetPasswordTokenService) {
        this.passwordEncoder = passwordEncoder;
        this.messageSource = messageSource;
        this.userService = userService;
        this.resetPasswordTokenService = resetPasswordTokenService;
    }

    /**
     * 驗證token, 通過就把user導到重設密碼頁面
     * @param token: Token from link inside email
     * @return String
     */
    @GetMapping("/resetPassword")
    public String resetPassword(@RequestParam("token") String token, Model model, Locale locale) {

        // Validate token
        String result = resetPasswordTokenService.validatePasswordResetToken(token);

        // sth went wrong
        if( result != null ){
            String message = messageSource.getMessage("auth.message", null, locale) + ( result == null ? "" : result)  ;
            model.addAttribute("message", message);
            return "login/login";
        }

        model.addAttribute("token", token);
        return "login/resetPassword";
    }


    @ResponseBody
    @PostMapping("/resetPassword")
    public GenericResponse processResetPassword(@Valid @RequestBody final PasswordDto passwordDto, final Locale locale) {

        // Validate token
        String result = resetPasswordTokenService.validatePasswordResetToken(passwordDto.getToken());
        // sth went wrong
        if( result != null )
            return new GenericResponse("fail", messageSource.getMessage("auth.message" , null, locale) + ( result == null ? "" : result) );

        // Get user by token
        User user = userService.getUserByResetPasswordToken(passwordDto.getToken());
        if( user != null  ){
            // check if new Password different with original one
            if( !passwordEncoder.matches(passwordDto.getPassword(), user.getPassword() ) ){
                // change password
                userService.changeUserPassword(user, passwordDto.getPassword());
                return new GenericResponse("successful", messageSource.getMessage("message.resetPasswordSuc", null, locale));
            }
            else {
                // refuse to change
                return new GenericResponse("fail", messageSource.getMessage("message.password.same", null, locale));
            }
        } else
            return new GenericResponse("fail", messageSource.getMessage("auth.userNotFound.message", null, locale));
    }

}
