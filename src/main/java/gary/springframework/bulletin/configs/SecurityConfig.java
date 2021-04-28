package gary.springframework.bulletin.configs;

import gary.springframework.bulletin.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "gary.springframework.bulletin.security" )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfig() {
        super();
    }

    /**
     * 可在這開放靜態資源訪問
     * @param web:
     */
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/model/**", "/webjars/**", "/resources/**", "/css/**");
    }

    /**
     * AuthenticationManagerBuilder會幫我們依照我們的需求建立AuthenticationManager
     * 而AuthenticationManager會依照我們驗證方式去決定要呼叫哪個AuthenticationProvider去處理
     *
     * 也可用來設定 inMemory Authentication 之帳號密碼角色, 提供最初的帳號登入測試
     * @param auth:
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        // 設定提供權限認證的bean(class)
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // authorizeRequests越特定的條件要放在越前面
                .antMatchers("/admin/**").hasRole("ADMIN") // 訪問這個url的需要有ADMIN的角色
                .antMatchers("/login*", "/login/**", "/regist/**","/forgetPassword*", "/resetPassword*").permitAll() // 和一開始註冊相關流程的都不需要任何權限認證
                .antMatchers("/favicon*", "/h2-console/**").permitAll()
                .anyRequest().authenticated();


        http.formLogin()
            .loginPage("/login")       // 指定客製的登入PAGE URL
            .loginProcessingUrl("/login")   // 登入過程獲取登入訊息的Class, 利用post這個url去觸發Spring security幫我們驗證登入者
            .successHandler(authenticationSuccessHandler) // will redirect to home page here
            .failureHandler(authenticationFailureHandler)   // 登入失敗處理器, 在裡面設定失敗要導到哪個url
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/do_logout")
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll();
                // .deleteCookies()

        // .access 更強大更方便 之後可加入
        // Reference :  https://docs.spring.io/spring-security/site/docs/current/reference/html5/#el-access

        http.csrf().disable();                   // 不關閉就得把CSRF token包含進POST
        http.headers().frameOptions().disable(); // 讓我們可以正常看到h2的console
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
        authProvider.setHideUserNotFoundExceptions(false);
        authProvider.setPasswordEncoder(passwordEncoder());     // Used BCrypt now
        authProvider.setUserDetailsService(userDetailsService); // MyUserDetailsService, customize provider in our program
        return authProvider;
    }

    /**
     * 指定密碼要用哪種編碼
     * Strength 指定編碼強度 可以查看看Bcrypt的編碼強度數字範圍
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

}
