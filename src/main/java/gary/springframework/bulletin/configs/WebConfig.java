package gary.springframework.bulletin.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
public class WebConfig {

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
