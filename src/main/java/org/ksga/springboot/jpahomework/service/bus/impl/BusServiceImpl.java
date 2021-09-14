package org.ksga.springboot.jpahomework.service.bus.impl;

import lombok.extern.slf4j.Slf4j;
import org.ksga.springboot.jpahomework.dto.mapper.BusMapper;
import org.ksga.springboot.jpahomework.dto.model.bus.BusDto;
import org.ksga.springboot.jpahomework.model.bus.Bus;
import org.ksga.springboot.jpahomework.repository.bus.BusRepository;
import org.ksga.springboot.jpahomework.service.bus.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BusServiceImpl implements BusService {
    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BusMapper busMapper;

    @Transactional
    @Override
    public BusDto insert(BusDto busDto) {
        Bus bus = busMapper.busDtoToBus(busDto);
        bus = busRepository.save(bus);
        return busMapper.busToBusDto(bus);
    }

    @Override
    public BusDto findById(String id) {
        Optional<Bus> bus = busRepository.findById(id);
        return bus.map(value -> busMapper.busToBusDto(value)).orElse(null);
    }

    @Override
    public BusDto deleteById(String id) {
        BusDto busDto = findById(id);
        busRepository.deleteById(id);
        return busDto;
    }

    @Override
    public BusDto updateById(String id, BusDto busDto) {
        return null;
    }

    @Override
    public List<BusDto> findAllBuses() {
        List<Bus> buses = busRepository.findAll();
        return buses.isEmpty() ? null : busMapper.busesToBusDtos(buses);
    }

    @Override
    public Page<Bus> findAllBuses(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return busRepository.findAll(paging);
    }

    @Override
    public boolean existsByCode(String code) {
        return busRepository.existsByCode(code);
    }

//    @Override
//    public List<BusDto> findAllBuses(int page, int size) {
//        Pageable paging = PageRequest.of(page, size);
//        Page<Bus> bus = busRepository.findAll(paging);
//        List<Bus> buses = bus.getContent();
//        return buses.isEmpty() ? null : busMapper.busesToBusDtos(buses);
//    }
}
