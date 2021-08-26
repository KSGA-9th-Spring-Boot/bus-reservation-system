package org.ksga.springboot.jpahomework.dto.mapper;

import org.ksga.springboot.jpahomework.dto.model.bus.TripDto;
import org.ksga.springboot.jpahomework.model.bus.Trip;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface TripMapper {
    Trip tripDtoToTrip(TripDto tripDto);

    TripDto tripToTripDto(Trip trip);

    List<Trip> tripDtosToTrips(List<TripDto> tripDtos);

    List<TripDto> tripsToTripDtos(List<Trip> trips);
}
