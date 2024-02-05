package com.example.bpofficesservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class BPAccount {

    private Long id;
    private Long brokerageId;
    private String name;
    private Long ownerOfficeId;
    private BPOffice bpOffice;
    private Long salesLeadId;
    private BPUser salesLead;
    private Long serviceLeadId;
    private BPUser serviceLead;
    private Integer businessTypeId;
    private boolean activeInd;
    private String environment;

}
