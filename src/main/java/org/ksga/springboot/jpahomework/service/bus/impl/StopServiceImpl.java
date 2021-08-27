package org.ksga.springboot.jpahomework.service.bus.impl;

import org.ksga.springboot.jpahomework.dto.mapper.StopMapper;
import org.ksga.springboot.jpahomework.dto.model.bus.StopDto;
import org.ksga.springboot.jpahomework.model.bus.Stop;
import org.ksga.springboot.jpahomework.repository.bus.StopRepository;
import org.ksga.springboot.jpahomework.service.bus.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StopServiceImpl implements StopService {
    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private StopMapper stopMapper;

    @Override
    public StopDto addStop(StopDto stopDto) {
        Stop stop = stopMapper.stopDtoToStop(stopDto);
        stopRepository.save(stop);
        return stopDto;
    }

    @Override
    public StopDto findById(String id) {
        return null;
    }

    @Override
    public List<StopDto> findAllStops() {
        return null;
    }
}
