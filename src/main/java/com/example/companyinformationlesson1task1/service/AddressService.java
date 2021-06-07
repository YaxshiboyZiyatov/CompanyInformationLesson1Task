package com.example.companyinformationlesson1task1.service;

import com.example.companyinformationlesson1task1.entity.Address;
import com.example.companyinformationlesson1task1.payload.ApiResponse;
import com.example.companyinformationlesson1task1.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public List<Address> getAll(){
        List<Address> all = addressRepository.findAll();
        return all;
    }


    public Address getById(Integer id){
        Optional<Address> byId = addressRepository.findById(id);
        return byId.orElse(null);

    }
    public ApiResponse add(Address address){
        boolean exists = addressRepository.existsByStreetAndHomeNumber(address.getStreet(), address.getHomeNumber());
        if (exists){
            return new ApiResponse("Bunday address mavjud", false);
        }
        Address address1=new Address(address.getStreet(), address.getHomeNumber());
        addressRepository.save(address1);
        return new ApiResponse("saved", true);
    }
    public ApiResponse edit(Integer id, Address address){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        boolean exists = addressRepository.existsByStreetAndHomeNumber(address.getStreet(), address.getHomeNumber());
        if (exists){
            return new ApiResponse("Bunday address mavjud", false);
        }
        if (!optionalAddress.isPresent()){
            return new ApiResponse("Not found Address Id", false);
        }
        Address address1 = optionalAddress.get();
        address1.setStreet(address.getStreet());
        address1.setHomeNumber(address.getHomeNumber());
        addressRepository.save(address1);
        return new ApiResponse("saved", true);

    }
    public ApiResponse delete(Integer id){
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()){
            addressRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("Not found Id", false);
    }
}
