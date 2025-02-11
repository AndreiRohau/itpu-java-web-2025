package com.arohau.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="company_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String zipcode;

    @Column
    private String country;
}
