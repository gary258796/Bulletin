package gary.springframework.bulletin.normalstuff.event;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.normalstuff.util.Mail.SendType;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import java.util.Locale;

@Getter
public class OnSendMailEvent extends ApplicationEvent {

    private final String appUrl;

    private final Locale locale;

    private final User user;

    private final SendType sendType;

    private final String token;

    public OnSendMailEvent(String appUrl, Locale locale, User user, SendType sendType) {
        super(user);
        this.appUrl = appUrl;
        this.locale = locale;
        this.user = user;
        this.sendType = sendType;
        this.token = "";
    }

    public OnSendMailEvent(String appUrl, Locale locale, User user, SendType sendType, String token) {
        super(user);
        this.appUrl = appUrl;
        this.locale = locale;
        this.user = user;
        this.sendType = sendType;
        this.token = token;
    }
}
