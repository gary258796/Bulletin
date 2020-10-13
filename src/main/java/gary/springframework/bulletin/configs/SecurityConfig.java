//package gary.springframework.bulletin.configs;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
//        http.authorizeRequests().antMatchers("/").hasAnyRole()
//                .and().formLogin();
//
//    }
//}
