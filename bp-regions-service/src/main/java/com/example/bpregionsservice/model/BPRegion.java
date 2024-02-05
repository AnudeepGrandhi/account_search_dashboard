package com.example.bpregionsservice.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity(name="broker_region")
public class BPRegion {

    @Id
    @Column(name = "region_id")
    private Long id;
    private Long brokerageId;
    @JsonProperty("name")
    private String regionName;
    private String code;
    @Transient
    @JsonProperty("environment")
    private String environment;

}
