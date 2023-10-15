package com.tis.usecase.energyconsumption.controller;

import com.tis.usecase.energyconsumption.converter.MeterRequestConverter;
import com.tis.usecase.energyconsumption.domain.MeterRequest;
import com.tis.usecase.energyconsumption.service.MeterHandlerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/meter-readings")
@AllArgsConstructor
public class MeterHandlerRestController {

    private MeterHandlerService meterHandlerService;

    private MeterRequestConverter meterRequestConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReadings(@Valid @RequestBody List<MeterRequest> request) {
        meterHandlerService.saveAll(request.stream().map(meterRequestConverter::convert).collect(Collectors.toList()));
    }

}
