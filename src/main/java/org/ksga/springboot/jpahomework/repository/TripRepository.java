package org.ksga.springboot.jpahomework.repository;

import org.ksga.springboot.jpahomework.model.bus.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, String> {
}
