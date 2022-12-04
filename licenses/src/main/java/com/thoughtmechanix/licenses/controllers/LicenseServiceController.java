package com.thoughtmechanix.licenses.controllers;

import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.services.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {
    @Autowired
    private LicenseService licenseService;

    @GetMapping
    public List<License> getLicenses( @PathVariable("organizationId") String organizationId) {

        return licenseService.getLicensesByOrg(organizationId);
    }

    @GetMapping("test")
    public String x( @PathVariable("organizationId") String organizationId) {

        return "licenseService.getLicensesByOrg(organizationId);";
    }


    @GetMapping(value="/{licenseId}")
    public License getLicenses( @PathVariable("organizationId") String organizationId,
                                @PathVariable("licenseId") String licenseId) {
        return licenseService.getLicense(organizationId,licenseId, "");
    }

}
