package workbot.climbawayapi.climbaway.domain.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.League;
import workbot.climbawayapi.security.domain.model.entity.Scalers;

import java.util.List;

@Service
public interface LeagueService {
    List<League> getAll();
    League findById(Long id);
    List<League> findByClimbingGymId(Long id);
    League create(League league, Long climbingGymId, Long scaleId);
    League update(League league, Long id);
    League updateNumberParticipants(Long leagueId, Long numberParticipants);
    League deleteParticipant(Long leagueId, Long scaleId);
    League delete(Long id);

    // Requests
    List<League> findRequestsByScalerId(Long scaleId);
    List<Scalers> findRequestsByLeagueId(long leagueId);
    League createRequest(Long leagueId, Long scaleId);
    League acceptRequest(Long leagueId, Long scaleId);
    League rejectRequest(Long leagueId, Long scaleId);
}
