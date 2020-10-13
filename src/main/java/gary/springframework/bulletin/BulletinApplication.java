package gary.springframework.bulletin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

public class BulletinApplication {

    public static void main(String[] args) {
        SpringApplication.run(BulletinApplication.class, args);
    }

}
