package org.ksga.springboot.jpahomework.dto.mapper;

import org.ksga.springboot.jpahomework.dto.model.bus.StopDto;
import org.ksga.springboot.jpahomework.model.bus.Stop;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface StopMapper {
    StopDto stopToStopDto(Stop stop);
    Stop stopDtoToStop(StopDto stopDto);

    List<Stop> stopDtosToStops(List<StopDto> stopDtos);
    List<StopDto> stopsToStopDtos(List<Stop> stops);
}
