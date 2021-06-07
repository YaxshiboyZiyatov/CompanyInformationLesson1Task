package com.example.companyinformationlesson1task1.repository;

import com.example.companyinformationlesson1task1.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByCorpName(String corpName);
}
