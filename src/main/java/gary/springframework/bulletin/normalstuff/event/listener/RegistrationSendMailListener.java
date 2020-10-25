package gary.springframework.bulletin.normalstuff.event.listener;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.normalstuff.event.OnRegistrationSendMailEvent;
import gary.springframework.bulletin.normalstuff.util.SendEmailHelper;
import gary.springframework.bulletin.web.services.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationSendMailListener implements ApplicationListener<OnRegistrationSendMailEvent> {

    private SendEmailHelper emailHelper;
    private UserService userService;

    public RegistrationSendMailListener(UserService userService, SendEmailHelper emailHelper) {
        this.userService = userService;
        this.emailHelper = emailHelper;
    }

    /**
     * 監聽到這個Event的時候要做哪些事情
     * @param onRegistrationSendMailEvent
     */
    @Override
    public void onApplicationEvent(OnRegistrationSendMailEvent onRegistrationSendMailEvent) {
        sendConfirmRegistrationMail(onRegistrationSendMailEvent);
    }

    /**
     * 寄送註冊確認信給使用者 by 他所註冊之信箱
     * @param onRegistrationSendMailEvent
     */
    public void sendConfirmRegistrationMail(OnRegistrationSendMailEvent onRegistrationSendMailEvent) {

        // create token for this user
        User registeredUser = onRegistrationSendMailEvent.getUser();
        final String token = UUID.randomUUID().toString();
        userService.createVerificationTokenForUser(registeredUser, token);

        // send mail
        emailHelper.sendRegistrationEmail(onRegistrationSendMailEvent, registeredUser, token );
    }

}
