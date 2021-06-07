package com.example.companyinformationlesson1task1.service;

import com.example.companyinformationlesson1task1.entity.Address;
import com.example.companyinformationlesson1task1.entity.Company;
import com.example.companyinformationlesson1task1.payload.ApiResponse;
import com.example.companyinformationlesson1task1.payload.CompanyDto;
import com.example.companyinformationlesson1task1.repository.AddressRepository;
import com.example.companyinformationlesson1task1.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    public List<Company> getAll(){
        List<Company> all = companyRepository.findAll();
        return all;
    }


    public Company getById(Integer id){
        Optional<Company> byId = companyRepository.findById(id);
        return byId.orElse(null);

    }
    public ApiResponse add(CompanyDto companyDto){
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent()){
            return new ApiResponse("not found address id", false);
        }
        boolean exists = companyRepository.existsByCorpName(companyDto.getCorpName());
        if (exists){
            return new ApiResponse("Bunday CorpName mavjud", false);
        }
        Company company=new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorNAme(companyDto.getDirectorNAme());
        company.setAddress(optionalAddress.get());
        companyRepository.save(company);
        return new ApiResponse("saved", true);
    }
    public ApiResponse edit(Integer id, CompanyDto companyDto){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        boolean exists = companyRepository.existsByCorpName(companyDto.getCorpName());
        if (exists){
            return new ApiResponse("Bunday address mavjud", false);
        }
        if (!optionalAddress.isPresent()){
            return new ApiResponse("not found address id", false);
        }
        if (!optionalCompany.isPresent()){
            return new ApiResponse("Not found Id", false);
        }
        Company company = optionalCompany.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorNAme(companyDto.getDirectorNAme());
        company.setAddress(optionalAddress.get());
        companyRepository.save(company);
        return new ApiResponse("saved", true);

    }
    public ApiResponse delete(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()){
            companyRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("Not found Id", false);
    }
}
