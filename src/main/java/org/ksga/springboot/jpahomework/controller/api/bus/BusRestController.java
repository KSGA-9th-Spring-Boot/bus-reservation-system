package org.ksga.springboot.jpahomework.controller.api.bus;

import org.ksga.springboot.jpahomework.dto.mapper.BusMapper;
import org.ksga.springboot.jpahomework.dto.mapper.UserMapper;
import org.ksga.springboot.jpahomework.dto.model.bus.AgencyDto;
import org.ksga.springboot.jpahomework.dto.model.bus.BusDto;
import org.ksga.springboot.jpahomework.dto.model.user.UserDto;
import org.ksga.springboot.jpahomework.dto.request.BusRequest;
import org.ksga.springboot.jpahomework.dto.response.Response;
import org.ksga.springboot.jpahomework.model.bus.Agency;
import org.ksga.springboot.jpahomework.model.bus.Bus;
import org.ksga.springboot.jpahomework.security.service.UserDetailsImpl;
import org.ksga.springboot.jpahomework.service.bus.AgencyService;
import org.ksga.springboot.jpahomework.service.bus.BusService;
import org.ksga.springboot.jpahomework.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/buses")
public class BusRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private BusService busService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private BusMapper busMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Response<BusDto> insert(@RequestBody BusRequest busRequest,
                                   @AuthenticationPrincipal UserDetailsImpl currentUser) {
        if (currentUser == null) {
            return Response
                    .<BusDto>unauthorized()
                    .setErrors("No Authorized to do anything in this method.");
        } else {
            UserDto userDto = userService.findUserByEmail(currentUser.getEmail());
            AgencyDto agencyDto = agencyService.findAgencyByOwner(userDto);
            BusDto busDto = new BusDto()
                    .setCode(busRequest.getCode())
                    .setCapacity(busRequest.getCapacity())
                    .setMake(busRequest.getMake())
                    .setAgencyDto(agencyDto);
            busDto = busService.insert(busDto);
            return Response
                    .<BusDto>ok()
                    .setPayload(busDto);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PASSENGER')")
    @GetMapping
    public Response<List<BusDto>> findAllBuses(@AuthenticationPrincipal UserDetailsImpl currentUser) {
        List<BusDto> busDtos = busService.findAllBuses();
        if (busDtos == null || busDtos.isEmpty()) {
            return Response.notFound();
        }
        return Response
                .<List<BusDto>>ok()
                .setPayload(busDtos);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PASSENGER')")
    @GetMapping("/paging")
    public Response<List<BusDto>> findAllBuses(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        if (page > 0) {
            page--;
        }
        Page<Bus> busDtoPage = busService.findAllBuses(page, size);
        List<BusDto> busDtos = busMapper.busesToBusDtos(busDtoPage.getContent());
        if (busDtos.isEmpty()) {
            return Response.notFound();
        }
        Map<String, Object> meta = new HashMap<>();
        meta.put("page", page + 1);
        meta.put("size", size);
        meta.put("total_pages", busDtoPage.getTotalPages());
        meta.put("total_count", busDtoPage.getTotalElements());
        return Response
                .<List<BusDto>>ok()
                .setPayload(busDtos)
                .setMetadata(meta);
    }

}
