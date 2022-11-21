package workbot.climbawayapi.climbaway.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import workbot.climbawayapi.security.mapping.ScalersMapper;

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
    public NotificationsMapper notificationMapper(){
        return new NotificationsMapper();
    }
    @Bean
    public NewsMapper newsMapper(){ return new NewsMapper(); }
    @Bean
    public CommentMapper commentMapper(){ return new CommentMapper(); }
    @Bean
    public ImageMapper imageMapper(){ return new ImageMapper(); }
    @Bean
    public LeagueMapper leagueMapper(){ return new LeagueMapper(); }
    @Bean
    public MemberMapper memberMapper(){ return new MemberMapper(); }
    @Bean
    public CompetitionLeagueMapper competitionLeagueMapper(){ return new CompetitionLeagueMapper(); }
    @Bean
    public CompetitionLeagueRankingMapper competitionLeagueRankingMapper(){ return new CompetitionLeagueRankingMapper(); }
    @Bean
    public FeatureMapper featureMapper(){ return new FeatureMapper(); }
}
