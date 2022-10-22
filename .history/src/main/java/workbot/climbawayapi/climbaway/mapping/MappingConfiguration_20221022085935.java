package workbot.climbawayapi.climbaway.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("reservingMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ClimbingGymMapper climbingGymMapper(){
        return new ClimbingGymMapper();
    }
    @Bean
    public CategoryMapper categoryMapper(){
        return new CategoryMapper();
    }
    @Bean
    public NotificationMapper notificationMapper(){
        return new NotificationMapper();
    }
    @Bean
    public ScalersMapper scalersMapper(){
        return new ScalersMapper();
    }
}
