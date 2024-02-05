package com.example.bpbrokeragesservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name="brokerage_house")
public class BPBrokerage {

    @Id
    @Column(name = "brokerage_id")
    private Long id;
    @Column(name="name")
    @JsonProperty("name")
    private String brokerageName;
    private int activeInd;

}
