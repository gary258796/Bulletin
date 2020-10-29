package gary.springframework.bulletin.normalstuff.event.listener;

import gary.springframework.bulletin.normalstuff.event.OnSendMailEvent;
import gary.springframework.bulletin.normalstuff.util.Mail.SendEmailService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Determine which send type is , and call mailService to help
 */
@Component
public class SendMailListener implements ApplicationListener<OnSendMailEvent> {

    private final SendEmailService emailService;

    public SendMailListener(SendEmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * 監聽到這個Event的時候要做哪些事情
     * @param onSendMailEvent : 監聽到的發送信件事件
     */
    @Override
    public void onApplicationEvent(OnSendMailEvent onSendMailEvent) {

        switch (onSendMailEvent.getSendType()) {
            case send:
                emailService.sendRegistrationEmail(onSendMailEvent.getUser().getEmail(), onSendMailEvent.getAppUrl(),
                        onSendMailEvent.getLocale(), onSendMailEvent.getToken());
                break;
            case resend:
                emailService.resendRegistrationEmail(onSendMailEvent.getUser().getEmail(), onSendMailEvent.getAppUrl(),
                        onSendMailEvent.getLocale(), onSendMailEvent.getToken());
                break;
            case reset:
                emailService.sendResetPasswordEmail(onSendMailEvent.getUser().getEmail(), onSendMailEvent.getAppUrl(),
                        onSendMailEvent.getLocale(), onSendMailEvent.getToken());
                break;
        }

    }
}
