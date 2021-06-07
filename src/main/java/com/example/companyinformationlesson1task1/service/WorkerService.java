package com.example.companyinformationlesson1task1.service;

import com.example.companyinformationlesson1task1.entity.Address;
import com.example.companyinformationlesson1task1.entity.Department;
import com.example.companyinformationlesson1task1.entity.Worker;
import com.example.companyinformationlesson1task1.payload.ApiResponse;
import com.example.companyinformationlesson1task1.payload.WorkerDto;
import com.example.companyinformationlesson1task1.repository.AddressRepository;
import com.example.companyinformationlesson1task1.repository.DepartmentRepository;
import com.example.companyinformationlesson1task1.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Worker> getAll(){
        List<Worker> all = workerRepository.findAll();
        return all;
    }
    public Worker getById(Integer id){
        Optional<Worker> byId = workerRepository.findById(id);
        return byId.orElse(null);
    }
    public ApiResponse add(WorkerDto workerDto){
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalAddress.isPresent()) return new ApiResponse("not found address id", false);
        if (!optionalDepartment.isPresent()) return new ApiResponse("not found department id", false);

        boolean exists = workerRepository.existsByPoneNumber(workerDto.getPhoneNumber());
        if (exists){
            return new ApiResponse("this is phone number Already exists", false);
        }
        Worker worker=new Worker();
        worker.setName(workerDto.getName());
        worker.setPoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new ApiResponse("saved", true);

    }
    public ApiResponse edit(Integer id, WorkerDto workerDto){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()){
            return new ApiResponse("Not found worker id", false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalAddress.isPresent()) return new ApiResponse("not found address id", false);
        if (!optionalDepartment.isPresent()) return new ApiResponse("not found department id", false);

        boolean exists = workerRepository.existsByPoneNumber(workerDto.getPhoneNumber());
        if (exists){
            return new ApiResponse("this is phone number Already exists", false);
        }
        Worker worker = optionalWorker.get();
        worker.setName(workerDto.getName());
        worker.setPoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new ApiResponse("saved", true);
    }

    public ApiResponse delete(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isPresent()){
            addressRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("Not found Id", false);
    }
}
