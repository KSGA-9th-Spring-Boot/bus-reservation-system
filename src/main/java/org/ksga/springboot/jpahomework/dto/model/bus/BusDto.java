package org.ksga.springboot.jpahomework.dto.model.bus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class BusDto {
    private String code;
    private int capacity;
    private String make;
    private AgencyDto agencyDto;
}
