package org.ksga.springboot.jpahomework.repository.bus;

import org.ksga.springboot.jpahomework.model.bus.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends JpaRepository<Stop, String> {
    Stop findByCode(String code);
}
