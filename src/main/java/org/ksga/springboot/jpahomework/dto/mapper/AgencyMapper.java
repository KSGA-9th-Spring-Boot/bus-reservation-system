package org.ksga.springboot.jpahomework.dto.mapper;

import org.ksga.springboot.jpahomework.dto.model.bus.AgencyDto;
import org.ksga.springboot.jpahomework.model.bus.Agency;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AgencyMapper {
    Agency agencyDtoToAgency(AgencyDto agencyDto);

    AgencyDto agencyToAgencyDto(Agency agency);
}
