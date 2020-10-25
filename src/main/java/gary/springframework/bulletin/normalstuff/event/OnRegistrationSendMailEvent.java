package gary.springframework.bulletin.normalstuff.event;

import gary.springframework.bulletin.data.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
public class OnRegistrationSendMailEvent extends ApplicationEvent {

    private String appUrl;

    private Locale locale;

    private User user;

    public OnRegistrationSendMailEvent(User user, String appUrl, Locale locale) {
        super(user);
        this.appUrl = appUrl;
        this.locale = locale;
        this.user = user;
    }

}
