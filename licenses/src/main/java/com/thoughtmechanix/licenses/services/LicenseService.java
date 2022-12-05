package com.thoughtmechanix.licenses.services;

import com.thoughtmechanix.licenses.client.OrganizationDiscoveryClient;
import com.thoughtmechanix.licenses.client.OrganizationRestTemplateClient;
import com.thoughtmechanix.licenses.config.ServiceConfig;
import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.repository.LicenseRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LicenseService {

    private final LicenseRepository licenseRepository;

    private final ServiceConfig config;
    private final OrganizationRestTemplateClient organizationRestClient;


    private final OrganizationDiscoveryClient organizationDiscoveryClient;

    private Organization retrieveOrgInfo(String organizationId, String clientType){
        Organization organization = null;

        switch (clientType) {
            case "feign" -> {
                System.out.println("I am using the feign client and I don't work");
            }
            case "rest" -> {
                System.out.println("I am using the rest client");
                organization = organizationRestClient.getOrganization(organizationId);
            }
            case "discovery" -> {
                System.out.println("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
            }
            default -> organization = organizationRestClient.getOrganization(organizationId);
        }

        return organization;
    }
    public License getLicense(String organizationId,String licenseId, String clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        Organization org = retrieveOrgInfo(organizationId, clientType);
        license.setContactName(org.getContactName());
        license.setContactEmail(org.getContactEmail());
        license.setContactPhone(org.getContactPhone());
        license.setComment(config.getExampleProperty());
        return license;
    }

    public List<License> getLicensesByOrg(String organizationId){
        return licenseRepository.findByOrganizationId( organizationId );
    }

    public void saveLicense(License license){
        license.setLicenseId( UUID.randomUUID().toString());
        licenseRepository.save(license);
    }

    public void updateLicense(License license){
      licenseRepository.save(license);
    }

    public void deleteLicense(License license){
        licenseRepository.deleteById(license.getLicenseId());
    }

}
