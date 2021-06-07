package com.example.companyinformationlesson1task1.repository;

import com.example.companyinformationlesson1task1.entity.Address;
import com.example.companyinformationlesson1task1.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    boolean existsByPoneNumber(String poneNumber);
}
