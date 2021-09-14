package org.ksga.springboot.jpahomework.dto.mapper;

import org.ksga.springboot.jpahomework.dto.model.bus.TripDto;
import org.ksga.springboot.jpahomework.model.bus.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface TripMapper {
    Trip tripDtoToTrip(TripDto tripDto);

    @Mapping(target = "sourceStopName", source = "sourceStop.name")
    @Mapping(target = "sourceStopCode", source = "sourceStop.code")
    @Mapping(target = "destinationStopName", source = "destStop.name")
    @Mapping(target = "destinationStopCode", source = "destStop.code")
    @Mapping(target = "busCode", source = "bus.code")
    @Mapping(target = "agencyCode", source = "agency.code")
    TripDto tripToTripDto(Trip trip);

    List<Trip> tripDtosToTrips(List<TripDto> tripDtos);

    List<TripDto> tripsToTripDtos(List<Trip> trips);
}
