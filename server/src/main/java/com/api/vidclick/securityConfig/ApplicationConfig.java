package com.api.vidclick.securityConfig;


import com.api.vidclick.models.Creator;
import com.api.vidclick.repositories.CreatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final CreatorRepository repository;
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with such user name does not exits."));
    }
}
