package com.tis.usecase.energyconsumption.controller;

import com.tis.usecase.energyconsumption.converter.ProfileConverter;
import com.tis.usecase.energyconsumption.converter.ProfileResponseConverter;
import com.tis.usecase.energyconsumption.domain.ProfileRequest;
import com.tis.usecase.energyconsumption.domain.ProfileResponse;
import com.tis.usecase.energyconsumption.service.ProfileHandlerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/profiles")
@AllArgsConstructor
public class ProfileHandlerRestController {

    private ProfileHandlerService profileHandlerService;

    private ProfileConverter profileConverter;

    private ProfileResponseConverter profileResponseConverter;

    @GetMapping
    public List<ProfileResponse> findProfiles() {
        return profileHandlerService.findAll().stream().map(profileResponseConverter::convert).collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public ProfileResponse findByName(@PathVariable String name) {
        return profileResponseConverter.convert(profileHandlerService.findByName(name));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAll(@Valid @RequestBody List<ProfileRequest> request) {
        profileHandlerService.saveAll(request.stream().map(profileConverter::convert).collect(Collectors.toList()));
    }

}
