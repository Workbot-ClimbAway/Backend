package workbot.climbawayapi.climbaway.domain.service;

import org.springframework.stereotype.Service;


import workbot.climbawayapi.climbaway.domain.model.entity.Notification;


import workbot.climbawayapi.climbaway.domain.model.entity.Scalers;

import java.util.List;

@Service
public interface ScalersService {

    List<Scalers> getAll(); // Get all scalers to list
    Scalers findById(long id); 
    Scalers create(Scalers scalers); // ADD SCALER
    Boolean findByEmail(String email);
    S findByEmailAndPassword(String email, String password);
    Scalers update(long id, Scalers scalers);
    Scalers delete(long id);
}
