package gary.springframework.bulletin.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 可用來設定 inMemory Authentication 之帳號密碼角色, 提供最初的帳號登入測試
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService( userDetailsService );

        // 也可以在這邊存取DB, 並且比對登入資訊, 之後會繼續實作
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            // authorizeRequests越特定的條件要放在越前面
            // TODO:model為靜態資源相關,所以開放,之後可改成除了regist login相關的 其他需要有權限
            .antMatchers("/admin/**").hasRole("ADMIN") // 訪問這個url的需要有ADMIN的角色
            .antMatchers("/login*", "/regist*", "/model/**", "/favicon*", "/h2-console/**","/invalidToken").permitAll()
            .anyRequest().authenticated();

        http.csrf().disable();                   // 不關閉就得把CSRF token包含進POST

        http.headers().frameOptions().disable(); // 讓我們可以正常看到h2的console

//                .and()
//                .formLogin()
//                    .loginPage("/login") // 指定客製的登入PAGE URL
//                    .loginProcessingUrl("/processing_login") // 登入過程 獲取登入訊息的Class
//                    .defaultSuccessUrl("/home") // 登入成功之後導到此頁面
//                    .failureUrl("") // 登入失敗, 導到此頁面
//                .and()
//                .logout().permitAll();
    }

    /**
     * 指定密碼要用哪種編碼
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
