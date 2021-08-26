package org.ksga.springboot.jpahomework.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class TripRequest {
    private String sourceStop;
    private String destinationStop;
    private String busCode;
    private int tripDuration;
    private int tripFare;
}
