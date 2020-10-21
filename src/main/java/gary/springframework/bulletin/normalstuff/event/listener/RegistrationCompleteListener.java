package gary.springframework.bulletin.normalstuff.event.listener;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.normalstuff.event.OnRegistrationCompleteEvent;
import gary.springframework.bulletin.normalstuff.util.SendEmailHelper;
import gary.springframework.bulletin.web.services.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationCompleteListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private SendEmailHelper emailHelper;
    private UserService userService;

    public RegistrationCompleteListener(UserService userService, SendEmailHelper emailHelper) {
        this.userService = userService;
        this.emailHelper = emailHelper;
    }

    /**
     * 監聽到這個Event的時候要做哪些事情
     * @param onRegistrationCompleteEvent
     */
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        sendConfirmRegistrationMail(onRegistrationCompleteEvent);
    }

    /**
     * 寄送註冊確認信給使用者 by 他所註冊之信箱
     * 並且開啟Async讓寄信的動作非同步, 不讓使用者等信件真的送出才能往下走
     * @param onRegistrationCompleteEvent
     */
    @Async
    public void sendConfirmRegistrationMail(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {

        // create token for this user
        User registeredUser = onRegistrationCompleteEvent.getUser();
        final String token = UUID.randomUUID().toString();
        userService.createVerificationTokenForUser(registeredUser, token);

        // send mail
        emailHelper.sendRegistrationEmail(onRegistrationCompleteEvent, registeredUser, token );
    }

}
