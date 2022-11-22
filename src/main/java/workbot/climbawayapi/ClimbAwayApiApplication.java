package workbot.climbawayapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@EnableJpaAuditing
public class ClimbAwayApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClimbAwayApiApplication.class, args);
    }

}
