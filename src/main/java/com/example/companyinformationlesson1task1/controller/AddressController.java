package com.example.companyinformationlesson1task1.controller;

import com.example.companyinformationlesson1task1.entity.Address;
import com.example.companyinformationlesson1task1.payload.ApiResponse;
import com.example.companyinformationlesson1task1.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping
    public HttpEntity<?> get() {
        return ResponseEntity.ok(addressService.getAll());

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(addressService.getById(id));
    }

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody Address address) {
        ApiResponse apiResponse = addressService.add(address);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@Valid @PathVariable Integer id, @RequestBody Address address) {
        ApiResponse apiResponse = addressService.edit(id, address);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);

    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleted(@PathVariable Integer id) {
        ApiResponse delete = addressService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(delete);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
