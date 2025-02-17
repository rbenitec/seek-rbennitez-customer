package com.rbenitez.lambdas.northwindx.service;

import com.rbenitez.lambdas.northwindx.entities.Customers;
import com.rbenitez.lambdas.northwindx.repository.CustomersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomersService {

    private final CustomersRepository customersRepository;

    public List<Customers> getCustomers(){
        return customersRepository.findAll();
    }

    public List<Customers> findByContactTitle(String contactTitle){
        return customersRepository.findAll().stream()
                .filter(customers -> customers.getContactTitle().contains(contactTitle))
                .collect(Collectors.toList());
    }

    public Customers findCustomerById(String customerId){
        return customersRepository.findById(customerId).get();
    }

    public Map<String, Long>  findCustomerByHeader (String header){
        Map<String, Long> result = new HashMap<>();
        Integer quantity = 0;
        switch (header){
            case "ContactTitle" :
                result = customersRepository.findAll().stream()
                        .collect(Collectors.groupingBy(
                                Customers::getContactTitle, Collectors.counting()
                        ));
                break;
            case "City":
                result = customersRepository.findAll().stream()
                        .collect(Collectors.groupingBy(
                                Customers::getCity, Collectors.counting()
                        ));
                break;
            case "Country":
                result = customersRepository.findAll().stream()
                        .collect(Collectors.groupingBy(
                                Customers::getCountry, Collectors.counting()
                        ));
                break;
        }
        return result;
    }
}
