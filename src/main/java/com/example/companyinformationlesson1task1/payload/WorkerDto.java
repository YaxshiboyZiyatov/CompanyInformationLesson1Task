package com.example.companyinformationlesson1task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {

    @NotNull(message = "Name ni kiriting!")
    private String name;

    @NotNull(message = "phoneNumber ni kiriting!")
    private String phoneNumber;

    @NotNull(message = "address Id ni kiriting!")
    private Integer addressId;

     @NotNull(message = "Department Id ni kiriting!")
    private Integer departmentId;


}
