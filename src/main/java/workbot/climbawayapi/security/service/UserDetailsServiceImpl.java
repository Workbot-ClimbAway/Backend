package workbot.climbawayapi.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import workbot.climbawayapi.security.domain.model.entity.Scalers;
import workbot.climbawayapi.security.domain.persistence.ScalersRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ScalersRepository scalersRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Scalers scalers= scalersRepository.findByEmail(email);
        if (scalersRepository.findByEmail(email) == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        System.out.println(scalers);
        return new UserDetailsImpl(scalers);
    }
}
