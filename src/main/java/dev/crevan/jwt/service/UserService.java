package dev.crevan.jwt.service;

import dev.crevan.jwt.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(final String username);

    User findById(final Long id);

    void delete(final Long id);
}
