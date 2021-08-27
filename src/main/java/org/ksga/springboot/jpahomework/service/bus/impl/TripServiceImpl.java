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
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

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
}
