package org.ksga.springboot.jpahomework.repository.bus;

import org.ksga.springboot.jpahomework.model.bus.Agency;
import org.ksga.springboot.jpahomework.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, String> {
    Optional<Agency> findByOwner(User owner);
}
