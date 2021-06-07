package com.example.companyinformationlesson1task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @NotNull(message = "corpName ni kiriting!")
    private String corpName;

    @NotNull(message = "directorNAme ni kiriting!")
    private String directorNAme;

    @NotNull(message = "addressId ni kiriting!")
    private Integer addressId;
}
