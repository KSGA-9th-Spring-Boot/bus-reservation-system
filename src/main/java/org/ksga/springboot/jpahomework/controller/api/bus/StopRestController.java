package org.ksga.springboot.jpahomework.controller.api.bus;

import org.ksga.springboot.jpahomework.dto.model.bus.StopDto;
import org.ksga.springboot.jpahomework.dto.request.StopRequest;
import org.ksga.springboot.jpahomework.dto.response.Response;
import org.ksga.springboot.jpahomework.service.bus.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stops")
public class StopRestController {
    @Autowired
    private StopService stopService;

    @PostMapping
    public Response<StopDto> addStop(@RequestBody StopRequest stopRequest) {
        StopDto stopDto = new StopDto()
                .setCode(stopRequest.getCode())
                .setDetail(stopRequest.getDetail())
                .setName(stopRequest.getName());
        stopService.addStop(stopDto);
        return Response
                .<StopDto>ok()
                .setPayload(stopDto);
    }
}
