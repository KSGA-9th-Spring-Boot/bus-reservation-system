package org.ksga.springboot.jpahomework.repository;

import org.ksga.springboot.jpahomework.model.user.Role;
import org.ksga.springboot.jpahomework.model.user.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(UserRoles role);
}
