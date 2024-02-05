package com.example.bpaccountsservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name="user_account")
public class BPUser {

    @Id
    @Column(name="user_id")
    private Long id;
    private String firstName;
    private String lastName;
    @Column(name="user_name")
    private String email;
}
