package dev.crevan.jwt.service.impl;

import dev.crevan.jwt.model.Role;
import dev.crevan.jwt.model.Status;
import dev.crevan.jwt.model.User;
import dev.crevan.jwt.repository.RoleRepository;
import dev.crevan.jwt.repository.UserRepository;
import dev.crevan.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User register(final User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.save(user);
        log.info("In register - user {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("In getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(final String username) {
        User result = userRepository.findByUsername(username);
        log.info("In findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(final Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("In findById - null user found by id: {}", id);
            return null;
        }
        log.info("In findById - user: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(final Long id) {
        userRepository.deleteById(id);
        log.info("In delete - user with id: {} successfully deleted", id);
    }
}
