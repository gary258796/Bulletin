package gary.springframework.bulletin.normalstuff.util.Mail;

import gary.springframework.bulletin.data.model.pojo.EmailFieldPojo;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Simplified SendMailHelper to Only send mail
 * Put all the messages work into SendMailService
 */
@Component
public class SendEmailHelper {

    private final JavaMailSender mailSender;

    public SendEmailHelper(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }

    /**
     * Async讓寄信的動作非同步, 不讓使用者等信件真的送出才能往下走
     */
    @Async
    public void SendMail(EmailFieldPojo emailField) {
        sendMail( constructEmail(emailField) );
    }

    /**
     * Build up a email end return
     * @param emailField
     * @return
     */
    private SimpleMailMessage constructEmail(EmailFieldPojo emailField) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo( emailField.getToAddress() );
        email.setSubject( emailField.getSubject() );
        email.setText( emailField.getText() );
        email.setFrom( emailField.getFromAddress() );
        return email;
    }

    /**
     * Send the email
     * @param email
     */
    private void sendMail(SimpleMailMessage email) {
        try {
            mailSender.send(email);
        }
        catch (MailException e) {
            System.out.println("\n >>> Error When Sending email : " + e);
        }
    }
}
