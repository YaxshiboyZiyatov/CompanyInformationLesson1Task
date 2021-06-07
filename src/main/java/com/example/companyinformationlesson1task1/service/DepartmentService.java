package com.example.companyinformationlesson1task1.service;

import com.example.companyinformationlesson1task1.entity.Company;
import com.example.companyinformationlesson1task1.entity.Department;
import com.example.companyinformationlesson1task1.payload.ApiResponse;
import com.example.companyinformationlesson1task1.payload.DepartmentDto;
import com.example.companyinformationlesson1task1.repository.CompanyRepository;
import com.example.companyinformationlesson1task1.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getAll(){
        List<Department> all = departmentRepository.findAll();
        return all;
    }
    public Department getById(Integer id){
        Optional<Department> byId = departmentRepository.findById(id);
        return byId.orElse(null);
    }
    public ApiResponse add(DepartmentDto departmentDto){
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        boolean exists = departmentRepository.existsByName(departmentDto.getName());
        if (exists){
            return new ApiResponse("Bunday Department mavjud", false);
        }
        if (!optionalCompany.isPresent()) return new ApiResponse("not found id", false);
        Department department=new Department();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("saved", true);
    }
    public ApiResponse edit(Integer id, DepartmentDto departmentDto){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        boolean exists = departmentRepository.existsByName(departmentDto.getName());
        if (exists){
            return new ApiResponse("Bunday Department mavjud", false);
        }
        if (!optionalDepartment.isPresent()){
            return new ApiResponse("Not found Department Id", false);
        }
        if (!optionalCompany.isPresent()){
            return new ApiResponse("Not found copmany Id", false);
        }
        Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("saved", true);

    }
    public ApiResponse delete(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()){
            departmentRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("Not found Id", false);
    }
}
