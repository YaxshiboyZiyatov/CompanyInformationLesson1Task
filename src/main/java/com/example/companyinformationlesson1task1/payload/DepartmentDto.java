package com.example.companyinformationlesson1task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    @NotNull(message = "Name ni kiriting!")
    private String name;

    @NotNull(message = "Company Id ni kiriting!")
    private Integer companyId;
}
