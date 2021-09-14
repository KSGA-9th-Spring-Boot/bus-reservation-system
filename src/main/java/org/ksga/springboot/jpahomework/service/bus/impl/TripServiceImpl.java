package org.ksga.springboot.jpahomework.service.bus.impl;

import org.ksga.springboot.jpahomework.dto.mapper.TripMapper;
import org.ksga.springboot.jpahomework.dto.model.bus.TripDto;
import org.ksga.springboot.jpahomework.model.bus.Agency;
import org.ksga.springboot.jpahomework.model.bus.Bus;
import org.ksga.springboot.jpahomework.model.bus.Stop;
import org.ksga.springboot.jpahomework.model.bus.Trip;
import org.ksga.springboot.jpahomework.repository.bus.AgencyRepository;
import org.ksga.springboot.jpahomework.repository.bus.StopRepository;
import org.ksga.springboot.jpahomework.repository.bus.TripRepository;
import org.ksga.springboot.jpahomework.repository.bus.BusRepository;
import org.ksga.springboot.jpahomework.service.bus.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private TripMapper tripMapper;

    @Override
    public TripDto addTrip(TripDto tripDto) {
        Bus bus = busRepository.findById(tripDto.getBusCode()).orElse(null);
        Stop sourceStop = stopRepository.findByCode(tripDto.getSourceStopCode());
        Stop destStop = stopRepository.findByCode(tripDto.getDestinationStopCode());

        Trip trip = tripMapper.tripDtoToTrip(tripDto);
        trip.setBus(bus);
        trip.setSourceStop(sourceStop);
        trip.setDestStop(destStop);

        System.out.println(trip);

        tripRepository.save(trip);
        return tripMapper.tripToTripDto(trip);
    }

    @Override
    public List<TripDto> findAllTrips() {
        List<Trip> trips = tripRepository.findAll();
        System.out.println(trips);
        List<TripDto> tripDtos = tripMapper.tripsToTripDtos(trips);
        return tripDtos.isEmpty() ? null : tripDtos;
    }

    @Override
    public List<TripDto> findAllTrips(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Trip> trips = tripRepository.findAll(pageable);
        return tripMapper.tripsToTripDtos(trips.getContent());
    }

    @Transactional
    @Override
    public TripDto updateTrip(String id, TripDto tripDto) {
        try {
            Trip trip = tripRepository.getById(id);
            Bus bus = busRepository.findByCode(tripDto.getBusCode());
            Stop sourceStop = stopRepository.findByCode(tripDto.getSourceStopCode());
            Stop destStop = stopRepository.findByCode(tripDto.getDestinationStopCode());

            trip.setJourneyTime(tripDto.getJourneyTime());
            trip.setFare(trip.getFare());
            trip.setBus(bus);
            trip.setSourceStop(sourceStop);
            trip.setDestStop(destStop);

            tripRepository.save(trip);
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public TripDto findById(String id) {
        Trip trip = tripRepository.getById(id);
        return tripMapper.tripToTripDto(trip);
    }

    @Override
    public long count() {
        return tripRepository.count();
    }

    @Override
    public TripDto deleteById(String id) {
        Optional<Trip> trip = tripRepository.findById(id);
        try {
            tripRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return trip.map(value -> tripMapper.tripToTripDto(value)).orElse(null);
    }
}
