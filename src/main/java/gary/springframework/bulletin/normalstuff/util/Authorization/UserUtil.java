package gary.springframework.bulletin.normalstuff.util.Authorization;

import gary.springframework.bulletin.data.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Get login user from authentication provide by spring security framework
 */
public class UserUtil {

    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication != null ? authentication.getPrincipal() : null;
        return principal instanceof User ? (User)principal : null;
    }
}
