package gary.springframework.bulletin.normalstuff.util;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.normalstuff.event.OnRegistrationSendMailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Locale;

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

    /**
     * 註冊時 寄送認證信
     * @param event
     * @param user
     * @param token
     */
    public void sendRegistrationEmail(final OnRegistrationSendMailEvent event, final User user, final String token) {

        final String toAddress = user.getEmail(); // 寄送目標信箱帳號
        final String subject = messageSource.getMessage("message.mail.subject", null, event.getLocale() ); // 主旨
        final String confirmationUrl = event.getAppUrl() + "/regist/registrationConfirm?token=" + token; // 確認連結
        final String message = messageSource.getMessage("message.mail.regSuccLink", null, event.getLocale()); // 信件內容
        final SimpleMailMessage email = constructEmail(toAddress, subject, message + " \r\n" + confirmationUrl, env.getProperty("support.email"));

        sendMail(email);
    }


    public void resendRegistrationEmail(String userEmail, String appUrl, String token, Locale locale) {

        final String subject = messageSource.getMessage("message.mail.resend.subject", null, locale ); // 主旨
        final String message = messageSource.getMessage("message.mail.resend.message", null, locale );
        final String confirmationUrl = appUrl + "/regist/registrationConfirm?token=" + token; // 確認連結

        final SimpleMailMessage email = constructEmail(userEmail, subject, message + " \r\n" + confirmationUrl, env.getProperty("support.email"));
        sendMail(email);
    }

    /**
     * Build up a email end return
     * @param toAddress
     * @param subject
     * @param text
     * @param fromAddress
     * @return
     */
    private SimpleMailMessage constructEmail(String toAddress, String subject, String text, String fromAddress) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo( toAddress );
        email.setSubject( subject );
        email.setText( text );
        email.setFrom( fromAddress );
        return email;
    }

    /**
     * Async讓寄信的動作非同步, 不讓使用者等信件真的送出才能往下走
     * @param email
     */
    @Async
    public void sendMail(SimpleMailMessage email) {
        try {
            mailSender.send(email);
        }
        catch (MailException e) {
            System.out.println("\n >>> Error When Sending email : " + e);
        }
    }
}
