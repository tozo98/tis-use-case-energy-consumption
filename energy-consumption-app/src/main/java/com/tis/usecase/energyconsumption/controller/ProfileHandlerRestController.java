package com.tis.usecase.energyconsumption.controller;

import com.tis.usecase.energyconsumption.converter.ProfileConverter;
import com.tis.usecase.energyconsumption.converter.ProfileResponseConverter;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.domain.ProfileRequest;
import com.tis.usecase.energyconsumption.domain.ProfileResponse;
import com.tis.usecase.energyconsumption.service.ProfileHandlerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/profiles")
@AllArgsConstructor
public class ProfileHandlerRestController {

    private ProfileHandlerService profileHandlerService;

    private ProfileConverter profileConverter;

    private ProfileResponseConverter profileResponseConverter;

    @GetMapping
    public List<ProfileResponse> retrieveProfiles() {
        return profileHandlerService.retrieveAll().stream().map(profileResponseConverter::convert).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAll(@RequestBody List<ProfileRequest> request) {
        profileHandlerService.saveAll(request.stream().map(profileConverter::convert).collect(Collectors.toList()));
    }

}
