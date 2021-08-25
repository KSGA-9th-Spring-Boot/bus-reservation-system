package org.ksga.springboot.jpahomework.service.bus;

import org.ksga.springboot.jpahomework.dto.model.bus.AgencyDto;
import org.ksga.springboot.jpahomework.dto.model.user.UserDto;

public interface AgencyService {
    AgencyDto insert(AgencyDto agencyDto);
    AgencyDto findAgencyByOwner(UserDto userDto);
}
