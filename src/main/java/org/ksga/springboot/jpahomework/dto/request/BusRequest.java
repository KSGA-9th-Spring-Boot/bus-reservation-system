package org.ksga.springboot.jpahomework.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusRequest {
    private String code;
    private int capacity;
    private String make;
}
