package com.freelance.driver_backend.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class OrganisationCreationRequest {

    @JsonProperty("long_name")
    private String longName;

    @JsonProperty("short_name")
    private String shortName;

    private String email;
    private String description;

    @JsonProperty("business_domains")
    private List<String> businessDomains;

    @JsonProperty("logo_url")
    private String logoUrl;

    @JsonProperty("legal_form")
    private String legalForm; // ex: "11"

    @JsonProperty("web_site_url")
    private String webSiteUrl;

    @JsonProperty("social_network")
    private String socialNetwork;

    @JsonProperty("business_registration_number")
    private String businessRegistrationNumber;

    @JsonProperty("tax_number")
    private String taxNumber;

    @JsonProperty("capital_share")
    private Double capitalShare;

    @JsonProperty("registration_date")
    private OffsetDateTime registrationDate;

    @JsonProperty("ceo_name")
    private String ceoName;

    @JsonProperty("year_founded")
    private OffsetDateTime yearFounded;

    private List<String> keywords;

    @JsonProperty("number_of_employees")
    private Integer numberOfEmployees;
}