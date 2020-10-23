package gary.springframework.bulletin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Spring 在呼叫onAuthenticationFailure之後, 會redirect到failureUrl設定的url去
 */
@Component("authenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        setDefaultFailureUrl("/login?error=true");

        super.onAuthenticationFailure(request, response, exception);

        // 從browser發出request決定locale是什麼
        final Locale locale = localeResolver.resolveLocale(request);

        String exceptionMsg = exception.getMessage();

        System.out.println(exceptionMsg);

        String errorMessage = messageSource.getMessage("message.bad.credential", null, locale);

        if( exceptionMsg.equals("Invalid username or password"))
            errorMessage = messageSource.getMessage("message.user.inValid", null, locale);
        else if( exceptionMsg.equals("User is disabled"))
            errorMessage = messageSource.getMessage("message.user.disabled", null, locale);
        else if( exceptionMsg.equals("User account has expired"))
            errorMessage = messageSource.getMessage("message.token.expired", null, locale);
        else if( exceptionMsg.equals("blocked"))
            errorMessage = messageSource.getMessage("message.user.blocked", null, locale);
        else if( exceptionMsg.equals("unusual location"))
            errorMessage = messageSource.getMessage("message.bad.location", null, locale);

        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);

    }
}
