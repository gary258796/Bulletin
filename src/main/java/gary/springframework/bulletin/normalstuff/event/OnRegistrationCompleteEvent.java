package gary.springframework.bulletin.normalstuff.event;

import gary.springframework.bulletin.data.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;

    private Locale locale;

    private User user;

    public OnRegistrationCompleteEvent(User user, String appUrl, Locale locale) {
        super(user);
        this.appUrl = appUrl;
        this.locale = locale;
        this.user = user;
    }

}
