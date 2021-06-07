package com.example.companyinformationlesson1task1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "street not")
    private String street;

    @NotNull(message = "home number not")
    private String homeNumber;

    public Address(Integer id, String street, String homeNumber) {
        this.id = id;
        this.street = street;
        this.homeNumber = homeNumber;
    }

    public Address(String street, String homeNumber) {
        this.street = street;
        this.homeNumber = homeNumber;
    }
}
