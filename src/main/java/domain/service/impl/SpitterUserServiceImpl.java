package domain.service.impl;

import domain.model.Spitter;
import domain.service.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SpitterUserServiceImpl implements UserDetailsService {

    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterUserServiceImpl(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Spitter spitter = spitterRepository.findByUserName(username);
        if (spitter != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_SPITTER"));

            return new User(
                    spitter.getUsername(),
                    spitter.getPassword(),
                    authorities);
        }

        throw new UsernameNotFoundException("User '" + username + "' not found.");
    }
}
