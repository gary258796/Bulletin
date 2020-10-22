package gary.springframework.bulletin.normalstuff.util;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.normalstuff.event.OnRegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmailHelper {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private Environment env;

    private final JavaMailSender mailSender;

    public SendEmailHelper(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }

    public void sendRegistrationEmail(final OnRegistrationCompleteEvent event, final User user, final String token) {

        final String toAddress = user.getEmail(); // 寄送目標信箱帳號
        final String subject = messageSource.getMessage("message.mail.subject", null, event.getLocale() ); // 主旨
        final String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token; // 確認連結
        final String message = messageSource.getMessage("message.mail.regSuccLink", null, event.getLocale()); // 信件內容

        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(toAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(env.getProperty("support.email"));

        try {
            mailSender.send(email);
        }
        catch (MailException e) {
            System.out.println("\n >>> Error When Sending email : " + e);
        }
    }
}
