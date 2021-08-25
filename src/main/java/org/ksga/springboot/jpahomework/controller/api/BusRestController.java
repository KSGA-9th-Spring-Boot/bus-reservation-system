package org.ksga.springboot.jpahomework.controller.api;

import org.ksga.springboot.jpahomework.dto.model.bus.BusDto;
import org.ksga.springboot.jpahomework.dto.request.BusRequest;
import org.ksga.springboot.jpahomework.dto.response.Response;
import org.ksga.springboot.jpahomework.model.bus.Bus;
import org.ksga.springboot.jpahomework.security.service.UserDetailsImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/buses")
public class BusRestController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public Response<BusDto> insert(@RequestBody BusRequest busRequest,
                                   @AuthenticationPrincipal UserDetailsImpl currentUser) {
        if (currentUser == null) {
            return Response
                    .<BusDto>unauthorized()
                    .setErrors("No Authorized to do this method.");
        } else {
            BusDto busDto = new BusDto()
                    .setCode(busRequest.getCode())
                    .setCapacity(busRequest.getCapacity())
                    .setMake(busRequest.getMake());
            return Response
                    .<BusDto>ok()
                    .setPayload(busDto);
        }
    }

}
