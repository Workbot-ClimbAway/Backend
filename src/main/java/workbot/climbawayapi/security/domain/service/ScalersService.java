package workbot.climbawayapi.security.domain.service;

import org.springframework.stereotype.Service;


import workbot.climbawayapi.security.domain.model.entity.Scalers;

import java.util.List;

@Service
public interface ScalersService {
    List<Scalers> getAll();
    Scalers findById(long id); 
    Scalers create(Scalers scalers);
    Scalers findByEmailAndPassword(String email, String password);
    Scalers findByEmail(String email);
    Scalers update(long id, Scalers scalers);
    Scalers delete(long id);
}
