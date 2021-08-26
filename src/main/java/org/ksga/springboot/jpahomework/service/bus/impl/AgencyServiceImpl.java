package org.ksga.springboot.jpahomework.service.bus.impl;

import org.ksga.springboot.jpahomework.dto.mapper.AgencyMapper;
import org.ksga.springboot.jpahomework.dto.mapper.UserMapper;
import org.ksga.springboot.jpahomework.dto.model.bus.AgencyDto;
import org.ksga.springboot.jpahomework.dto.model.user.UserDto;
import org.ksga.springboot.jpahomework.model.bus.Agency;
import org.ksga.springboot.jpahomework.model.user.User;
import org.ksga.springboot.jpahomework.repository.bus.AgencyRepository;
import org.ksga.springboot.jpahomework.service.bus.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AgencyServiceImpl implements AgencyService {
    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AgencyMapper agencyMapper;

    @Transactional
    @Override
    public AgencyDto insert(AgencyDto agencyDto) {
        Agency agency = agencyMapper.agencyDtoToAgency(agencyDto);
        agencyRepository.save(agency);
        return agencyMapper.agencyToAgencyDto(agency);
    }

    @Transactional
    @Override
    public AgencyDto findAgencyByOwner(UserDto userDto) {
        User owner = userMapper.userDtoToUser(userDto);
        Optional<Agency> agency = agencyRepository.findByOwner(owner);
        return agency.map(value -> agencyMapper.agencyToAgencyDto(value)).orElse(null);
    }
}
