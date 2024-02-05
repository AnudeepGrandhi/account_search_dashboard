package com.example.bpregionsservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BPOffice {

    private Long id;
    private Long brokerageId;
    private Long regionId;
    @JsonProperty("name")
    private String officeName;
    private String environment;

}
