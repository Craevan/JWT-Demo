package dev.crevan.jwt.repository;

import dev.crevan.jwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(final String name);
}
