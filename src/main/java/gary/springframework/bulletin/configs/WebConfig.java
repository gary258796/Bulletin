package gary.springframework.bulletin.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

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
     * ...還有幾種其他的LocaleResolver
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver slr = new CookieLocaleResolver();
        slr.setDefaultLocale(Locale.CHINESE);
        return slr;
    }

    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        // 指定message.properties的basename
        messageSource.setBasenames("classpath:/static/message/messages",
                                   "classpath:/static/message/validation");
        // 設定載入的資原始檔快取失效時間，-1的話為永不過期，預設為-1, 3600 為 3600 secs = 1 hour
        messageSource.setCacheSeconds(3600);
        // 設定編碼(不然中文會亂碼)
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;

    }


    /**
     * 設定Java校驗API的message來源, Spring Boot預設已經幫我們把messageSource準備好,但我們可以自己覆蓋掉預設配置
     */
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    /**
     * 注冊 MethodValidationPostProcessor bean 之後，Controller class 開頭加的 @Validated 與 method 裡針
     * 對 @RequestParam annotation 加的 validate annotation (e.g. @NotBlank, @NotNull ...etc)才會生效，
     * 沒注冊時就算程式碼裡有加這些 annotation 還是不會有作用
     *
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
