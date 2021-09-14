package org.ksga.springboot.jpahomework.repository.bus;

import org.ksga.springboot.jpahomework.model.bus.Agency;
import org.ksga.springboot.jpahomework.model.bus.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, String> {
    Bus findByCodeAndAgency(String busCode, Agency agency);
    Bus findByCode(String code);
    boolean existsByCode(String code);
}
