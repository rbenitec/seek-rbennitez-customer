package com.seek.rbenitez.customer.service.mapper;

import com.seek.rbenitez.customer.controller.dto.CustomerRequestDto;
import com.seek.rbenitez.customer.controller.dto.CustomerResponseDto;
import com.seek.rbenitez.customer.entities.Customer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequestDto customerRequest) {
        return Customer.builder()
                .companyName(customerRequest.getCompanyName())
                .names(customerRequest.getNames())
                .lastNames(customerRequest.getLastName())
                .age(customerRequest.getAge())
                .address(customerRequest.getAddress())
                .birthDate(customerRequest.getBirthDate())
                .dni(customerRequest.getDni())
                .country(customerRequest.getCountry())
                .phone(customerRequest.getPhone())
                .createdAt(LocalDateTime.now().toString())
                .build();
    }

    public CustomerResponseDto toDto(Customer customer, LocalDate dateLifeExpectancy) {
        return CustomerResponseDto.builder()
                .customerId(customer.getCustomerId())
                .companyName(customer.getCompanyName())
                .names(customer.getNames())
                .lastName(customer.getLastNames())
                .age(customer.getAge())
                .address(customer.getAddress())
                .birthdate(customer.getBirthDate())
                .country(customer.getCountry())
                .phone(customer.getPhone())
                .createdAt(customer.getCreatedAt())
                .dateLifeExpectancy(dateLifeExpectancy)
                .build();
    }
}
