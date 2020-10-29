package gary.springframework.bulletin.normalstuff.util.Mail;

import gary.springframework.bulletin.data.model.pojo.EmailFieldPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * 負責把信件的所有內容組成EmailFieldPojo並之後丟給emailHelper
 * emailHelper會再去組成一個mail entity並且寄送
 */
@Service
public class SendEmailService {

    @Autowired
    private Environment env;

    private MessageSource messageSource;
    private SendEmailHelper emailHelper;

    public SendEmailService(SendEmailHelper emailHelper, MessageSource messageSource) {
        this.emailHelper = emailHelper;
        this.messageSource = messageSource;
    }

    /**
     * Registration validate Email
     * @param email
     * @param appUrl
     * @param locale
     * @param token
     */
    public void sendRegistrationEmail(String email, String appUrl, Locale locale, final String token) {

        EmailFieldPojo emailField = new EmailFieldPojo();

        emailField.setToAddress( email );  // 寄送目標信箱帳號
        emailField.setSubject( messageSource.getMessage("message.mail.subject", null, locale) );
        emailField.setFromAddress( env.getProperty("support.email") );
        emailField.setText(messageSource.getMessage("message.mail.regSuccLink", null, locale) + " \r\n"
                + appUrl + "/regist/registrationConfirm?token=" + token ); // 信件內容 + 連結

        emailHelper.SendMail( emailField );
    }

    /**
     * Resend Registration email
     * @param email
     * @param appUrl
     * @param token
     * @param locale
     */
    public void resendRegistrationEmail(String email, String appUrl, Locale locale, final String token) {

        EmailFieldPojo emailField = new EmailFieldPojo();

        emailField.setToAddress( email );  // 寄送目標信箱帳號
        emailField.setSubject( messageSource.getMessage("message.mail.resend.subject", null, locale ) );
        emailField.setFromAddress( env.getProperty("support.email") );
        emailField.setText(messageSource.getMessage("message.mail.resend.message", null, locale ) + " \r\n"
                + appUrl + "/regist/registrationConfirm?token=" + token ); // 信件內容 + 連結

        emailHelper.SendMail( emailField );
    }

    /**
     * send Reset Password Email
     * @param email
     * @param appUrl
     * @param locale
     * @param token
     */
    public void sendResetPasswordEmail(String email, String appUrl, Locale locale, final String token) {

        EmailFieldPojo emailField = new EmailFieldPojo();

        emailField.setToAddress( email );  // 寄送目標信箱帳號
        emailField.setSubject( messageSource.getMessage("message.mail.resetPassword.subject", null, locale ) );
        emailField.setFromAddress( env.getProperty("support.email") );
        emailField.setText(messageSource.getMessage("message.mail.reset.message", null, locale ) + " \r\n"
                + appUrl + "/resetPassword?token=" + token ); // 信件內容 + 連結

        emailHelper.SendMail( emailField );
    }

}
