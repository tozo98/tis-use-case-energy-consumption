package com.tis.usecase.energyconsumption.controller;

import com.tis.usecase.energyconsumption.domain.ProfileRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileHandlerRestController {

    @GetMapping
    public List<ProfileRequest> retrieveProfiles() {
        List<ProfileRequest> result = new ArrayList<>();
        ProfileRequest req = new ProfileRequest();
        req.setName("A");
        Map<String, Double> fractions = new HashMap<>();
        fractions.put(Month.JANUARY.toString().substring(0,3), 0.2);
        fractions.put(Month.FEBRUARY.toString().substring(0,3), 0.1);
        req.setFractions(fractions);
        result.add(req);
        return result;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAll(@RequestBody List<ProfileRequest> request) {
        // TODO
    }

}
