package org.ksga.springboot.jpahomework.dto.model.bus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.ksga.springboot.jpahomework.dto.model.user.UserDto;

import java.util.Set;

@Getter
@Setter
@ToString
public class AgencyDto {
    private String code;

    private String name;

    private String details;

    private UserDto owner;

    private Set<BusDto> buses;
}