package com.example.bpofficesservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity(name="broker_office")
public class BPOffice {

    @Id
    @Column(name="office_id")
    private Long id;
    private Long brokerageId;
    private Long regionId;
    @JsonProperty("name")
    private String officeName;
    @Transient
    private String environment;

}
