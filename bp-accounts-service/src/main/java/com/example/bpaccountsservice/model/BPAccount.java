package com.example.bpaccountsservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity(name="employer")
@Data
public class BPAccount {

    @Id
    @Column(name = "employer_id")
    private Long id;
    private Long brokerageId;
    private String name;
    @Column(name="owner_office_id")
    private Long ownerOfficeId;
    @ManyToOne
    @JoinColumn(name="owner_office_id", referencedColumnName = "office_id", insertable=false, updatable=false)
    private BPOffice bpOffice;
    @Column(name="sales_lead_id")
    private Long salesLeadId;
    @ManyToOne
    @JoinColumn(name="sales_lead_id", referencedColumnName = "user_id", insertable=false, updatable=false)
    private BPUser salesLead;
    @Column(name="service_lead_id")
    private Long serviceLeadId;
    @ManyToOne
    @JoinColumn(name="service_lead_id", referencedColumnName = "user_id", insertable=false, updatable=false)
    private BPUser serviceLead;
    private Integer businessTypeId;
    private boolean activeInd;
    @Transient
    private String environment;

}
