package com.example.companyinformationlesson1task1.repository;

import com.example.companyinformationlesson1task1.entity.Address;
import com.example.companyinformationlesson1task1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    boolean existsByName(String name);
}
