package gary.springframework.bulletin.security;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.web.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component("authenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // add in the future if need
        HttpSession session = httpServletRequest.getSession();

        session.setAttribute("user", (User) authentication.getPrincipal() );

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/home"); // 回到 home page
    }
}
