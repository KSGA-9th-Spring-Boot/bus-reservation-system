package org.ksga.springboot.jpahomework.dto.mapper;

import org.ksga.springboot.jpahomework.dto.model.bus.BusDto;
import org.ksga.springboot.jpahomework.model.bus.Bus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface BusMapper {
    BusDto busToBusDto(Bus bus);

    @Mapping(target = "agency", source = "agencyDto")
    Bus busDtoToBus(BusDto busDto);

    List<Bus> busDtosToBuses(List<BusDto> busDtos);
    List<BusDto> busesToBusDtos(List<Bus> buses);
}
