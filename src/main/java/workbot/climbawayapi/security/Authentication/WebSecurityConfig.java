package workbot.climbawayapi.security.Authentication;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import workbot.climbawayapi.security.jwt.JWTAuthenticationFilter;
import workbot.climbawayapi.security.jwt.JWTAuthorizationFilter;

@Configuration
@AllArgsConstructor
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        System.out.println(authManager.getClass().getName());
        // cors().and().csrf().disable()
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");

        return http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**")
                .permitAll()
                .antMatchers("/api/v1/scaler**")
                .anonymous()
                .antMatchers("/api/v1/scaler/**")
                .anonymous()
                .antMatchers("/api/v1/login")
                .permitAll()
                .antMatchers("/api/v1/climbing-gyms**")
                .anonymous()
                .antMatchers("/api/v1/climbing-gyms/**")
                .anonymous()
                .antMatchers("/api/v1/categories**")
                .anonymous()
                .antMatchers("/api/v1/categories/**")
                .anonymous()
                .antMatchers("/api/v1/news/climbingGym/**")
                .anonymous()
                .antMatchers("/api/v1/images**")
                .anonymous()
                .antMatchers("/api/v1/images/**")
                .anonymous()
                .antMatchers("/api/v1/comments**")
                .anonymous()
                .antMatchers("/api/v1/comments/**")
                .anonymous()
                .antMatchers("/api/v1/features**")
                .anonymous()
                .antMatchers("/api/v1/features/**")
                .anonymous()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
