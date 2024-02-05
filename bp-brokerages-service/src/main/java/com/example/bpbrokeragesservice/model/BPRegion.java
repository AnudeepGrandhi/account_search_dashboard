package com.example.bpbrokeragesservice.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BPRegion {

    private Long id;
    private Long brokerageId;
    @JsonProperty("name")
    private String regionName;
    private String code;
    private String environment;

}
