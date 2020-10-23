package gary.springframework.bulletin.security;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserService userService;

    /**
     * userDetailsService的loadUserByUsername獲取使用者資訊並回傳給AuthenticationProvider驗證
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        final User user = userService.findByUserName(auth.getName());
        if((user == null)){
            throw new BadCredentialsException("Invalid username or password");
        }

        final Authentication result = super.authenticate(auth);
        return new UsernamePasswordAuthenticationToken(user, result.getCredentials(), result.getAuthorities());
    }


    /**
     * 是用來告訴ProviderManager自己支援哪種Authentication驗證, 讓ProviderManager知道要委託哪個Authentication Provider處理
     * 因為一個系統可能會有多個登入方式，因此會有多個驗證方式
     * @param authentication
     * @return Boolean , true if this AuthenticationProvider supports the indicated Authentication object
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
