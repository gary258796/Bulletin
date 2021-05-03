package gary.springframework.bulletin.security;

import gary.springframework.bulletin.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component("authenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        handle(httpServletRequest, httpServletResponse, authentication);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);     // By權限去選擇要導過去的Url

        if( response.isCommitted()){
            return ;
        }

        redirectStrategy.sendRedirect(request,response,targetUrl);
    }

    /**
     * 依照認證進來的資訊判斷是一般使用者還是管理者
     * @param authentication: 認證物件
     * @return
     */
    private String determineTargetUrl( Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // 判斷是User or Admin
        boolean isAdmin = authorities.stream().anyMatch(grantedAuthority ->
            grantedAuthority.getAuthority().equals("WRITE_PRIVILEGE")
        );

        if( !isAdmin ) return "/home/home.html" ; // 回到homePage
        else {
            return "/console";
        }
    }

}
