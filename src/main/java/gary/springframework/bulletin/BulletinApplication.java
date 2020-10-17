package gary.springframework.bulletin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
// exclude = SecurityAutoConfiguration.class用來取消自動的配置, 讓我們自己的配置得以生效
public class BulletinApplication {

    public static void main(String[] args) {
        SpringApplication.run(BulletinApplication.class, args);
    }

}
