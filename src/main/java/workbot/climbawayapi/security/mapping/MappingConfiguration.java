package workbot.climbawayapi.security.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import workbot.climbawayapi.climbaway.mapping.*;

@Configuration("securityMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ScalersMapper scalersMapper() { return new ScalersMapper(); }
}
