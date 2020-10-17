//package gary.springframework.bulletin.configs;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    /**
//     * 可用來設定 inMemoryAuthentication 之帳號密碼角色, 提供最初的帳號登入測試
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("1234").roles("ADMIN")
//                .and()
//                .withUser("user").password("1234").roles("USER");
//
//        // 也可以在這邊存取DB, 並且比對登入資訊, 之後會繼續實作
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                // authorizeRequests越特定的條件要放在越前面
//                .antMatchers("/admin/**").hasRole("ADMIN") // 訪問這個url的需要有ADMIN的角色
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("login/login") // 指定客製的登入頁面
//                .loginProcessingUrl("") // 登入過程 獲取登入訊息的Class
//                .defaultSuccessUrl("/home/home") // 登入成功之後導到此頁面
//                .failureUrl("") // 登入失敗, 導到此頁面
//            ; //
//    }
//
//    /**
//     * 指定密碼要用哪種編碼
//     * @return
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
