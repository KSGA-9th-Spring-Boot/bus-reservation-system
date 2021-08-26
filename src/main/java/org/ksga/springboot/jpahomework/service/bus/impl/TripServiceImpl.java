package org.ksga.springboot.jpahomework.service.bus.impl;

import org.ksga.springboot.jpahomework.dto.mapper.TripMapper;
import org.ksga.springboot.jpahomework.dto.model.bus.TripDto;
import org.ksga.springboot.jpahomework.model.bus.Trip;
import org.ksga.springboot.jpahomework.repository.TripRepository;
import org.ksga.springboot.jpahomework.service.bus.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripMapper tripMapper;

    @Override
    public TripDto addTrip(TripDto tripDto) {
        Trip trip = tripMapper.tripDtoToTrip(tripDto);
        tripRepository.save(trip);
        return tripMapper.tripToTripDto(trip);
    }

    @Override
    public List<TripDto> findAllTrips() {
        List<TripDto> tripDtos = tripMapper.tripsToTripDtos(tripRepository.findAll());
        return tripDtos.isEmpty() ? null : tripDtos;
    }

    @Override
    public List<TripDto> findAllTrips(int page, int size) {
        return null;
    }
}
