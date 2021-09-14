package org.ksga.springboot.jpahomework.service.bus;

import org.ksga.springboot.jpahomework.dto.model.bus.BusDto;
import org.ksga.springboot.jpahomework.model.bus.Bus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BusService {
    BusDto insert(BusDto busDto);
    BusDto findById(String id);
    BusDto deleteById(String id);
    BusDto updateById(String id, BusDto busDto);
    List<BusDto> findAllBuses();
    Page<Bus> findAllBuses(int page, int size);
    boolean existsByCode(String code);
}
