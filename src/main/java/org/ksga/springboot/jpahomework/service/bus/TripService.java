package org.ksga.springboot.jpahomework.service.bus;

import org.ksga.springboot.jpahomework.dto.model.bus.TripDto;

import java.util.List;

public interface TripService {
    TripDto addTrip(TripDto tripDto);
    List<TripDto> findAllTrips();
    List<TripDto> findAllTrips(int page, int size);
    TripDto updateTrip(String id, TripDto tripDto);
    TripDto findById(String id);
    long count();

    TripDto deleteById(String id);
}
