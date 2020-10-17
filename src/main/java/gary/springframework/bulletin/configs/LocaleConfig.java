package gary.springframework.bulletin.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class LocaleConfig implements WebMvcConfigurer {

    /**
     * 設定 Interceptor(攔截器) 來幫我們換語言
     * LocaleChangeInterceptor會變動locale,基於和login page一起傳送的參數, 在這我們是設定"lang"
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    /**
     * 設定預設的 locale
     * SessionLocaleResolver - remembers the locale throughout the session
     * CookieLocaleResolver - remember the user's locale every time they log in, and during the entire visit(存在cookie)
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver slr = new CookieLocaleResolver();
        slr.setDefaultLocale(Locale.CHINESE);
        return slr;
    }

}
