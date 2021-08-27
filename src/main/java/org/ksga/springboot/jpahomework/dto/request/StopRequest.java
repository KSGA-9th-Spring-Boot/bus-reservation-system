package org.ksga.springboot.jpahomework.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class StopRequest {
    private String code;
    private String name;
    private String detail;
}
