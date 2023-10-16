package com.tis.usecase.energyconsumption.controller;

import com.tis.usecase.energyconsumption.converter.MeterRequestConverter;
import com.tis.usecase.energyconsumption.converter.MeterResponseConverter;
import com.tis.usecase.energyconsumption.domain.MeterRequest;
import com.tis.usecase.energyconsumption.domain.MeterResponse;
import com.tis.usecase.energyconsumption.domain.MeterWithSingleMonthResponse;
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

    private MeterResponseConverter meterResponseConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAll(@Valid @RequestBody List<MeterRequest> request) {
        meterHandlerService.saveAll(request.stream().map(meterRequestConverter::convert).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public MeterResponse findById(@PathVariable Long id) {
        return meterResponseConverter.convert(meterHandlerService.findById(id));
    }

    @GetMapping("/{id}/{month}")
    public MeterWithSingleMonthResponse findByIdAndMonth(@PathVariable Long id, @PathVariable String month) {
        return meterResponseConverter.convertToSingleMonthResponse(meterHandlerService.findById(id), month);
    }

}
