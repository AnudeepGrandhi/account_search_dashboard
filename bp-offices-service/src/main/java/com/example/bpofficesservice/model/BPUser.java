package com.example.bpofficesservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class BPUser {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
