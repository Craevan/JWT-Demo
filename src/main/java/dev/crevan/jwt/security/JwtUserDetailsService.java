package dev.crevan.jwt.security;

import dev.crevan.jwt.model.User;
import dev.crevan.jwt.security.jwt.JwtUser;
import dev.crevan.jwt.security.jwt.JwtUserFactory;
import dev.crevan.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String name) throws UsernameNotFoundException {
        User user = userService.findByUsername(name);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + name + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("In loadUserByUsername - user with username: {} successfully loaded", name);

        return jwtUser;
    }
}
