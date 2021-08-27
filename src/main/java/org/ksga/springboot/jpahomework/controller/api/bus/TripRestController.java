package org.ksga.springboot.jpahomework.controller.api.bus;

import org.ksga.springboot.jpahomework.dto.model.bus.TripDto;
import org.ksga.springboot.jpahomework.dto.request.TripRequest;
import org.ksga.springboot.jpahomework.dto.response.Response;
import org.ksga.springboot.jpahomework.model.bus.Trip;
import org.ksga.springboot.jpahomework.service.bus.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trips")
public class TripRestController {

    @Autowired
    private TripService tripService;

    @PostMapping
    public Response<TripDto> addTrip(@RequestBody TripRequest tripRequest) {
        try {
            TripDto tripDto = new TripDto()
                    .setBusCode(tripRequest.getBusCode())
                    .setFare(tripRequest.getTripFare())
                    .setAgencyCode(tripRequest.getBusCode())
                    .setJourneyTime(tripRequest.getTripDuration())
                    .setSourceStopCode(tripRequest.getSourceStop())
                    .setDestinationStopCode(tripRequest.getDestinationStop());
            tripDto = tripService.addTrip(tripDto);
            return Response
                    .<TripDto>ok()
                    .setPayload(tripDto);
        } catch (Exception ex) {
            return Response
                    .<TripDto>exception()
                    .setErrors(ex.getMessage());
        }
    }

    @GetMapping
    public Response<List<TripDto>> findAllTrips() {
        List<TripDto> tripDtos = tripService.findAllTrips();
        if (tripDtos == null || tripDtos.isEmpty()) {
            return Response.notFound();
        }
        return Response
                .<List<TripDto>>ok()
                .setPayload(tripDtos);
    }

    @PutMapping("/{id}/update")
    public Response<TripDto> updateTrip(@PathVariable String id, @RequestBody TripRequest tripRequest) {
        TripDto tripDto = new TripDto()
                .setBusCode(tripRequest.getBusCode())
                .setFare(tripRequest.getTripFare())
                .setJourneyTime(tripRequest.getTripDuration())
                .setSourceStopCode(tripRequest.getSourceStop())
                .setDestinationStopCode(tripRequest.getDestinationStop());
        return Response
                .<TripDto>ok()
                .setPayload(tripService.updateTrip(id, tripDto));
    }

}
