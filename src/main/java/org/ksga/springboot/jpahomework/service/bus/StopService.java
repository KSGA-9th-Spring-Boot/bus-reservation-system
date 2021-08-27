package org.ksga.springboot.jpahomework.service.bus;

import org.ksga.springboot.jpahomework.dto.model.bus.StopDto;

import java.util.List;

public interface StopService {
    StopDto addStop(StopDto stopDto);
    StopDto findById(String id);
    List<StopDto> findAllStops();
}
