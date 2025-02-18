package com.seek.rbenitez.customer.service.impl;

import com.seek.rbenitez.customer.controller.dto.CustomerMetricsResponse;
import com.seek.rbenitez.customer.controller.dto.CustomerRequestDto;
import com.seek.rbenitez.customer.controller.dto.CustomerResponseDto;
import com.seek.rbenitez.customer.entities.Customer;
import com.seek.rbenitez.customer.exception.BusinessException;
import com.seek.rbenitez.customer.repository.CustomersRepository;
import com.seek.rbenitez.customer.service.CustomerService;
import com.seek.rbenitez.customer.service.mapper.CustomerMapper;
import com.seek.rbenitez.customer.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomersRepository customersRepository;
    private final CustomerMapper customerMapper;

    @Value("${spring.config.properties.business.average-life-expectancy}")
    private int averageLifeExpectancy;

    /**
     * Metodo para crear clientes.
     *
     * @param customerRequest
     * @return
     */
    @Override
    public CustomerResponseDto saveCustomer(CustomerRequestDto customerRequest) {
        Customer newCustomer = customerMapper.toEntity(customerRequest);
        Customer customerCreated = customersRepository.save(newCustomer);
        return customerMapper.toDto(customerCreated, null);
    }

    /**
     * Metodo para obtener la expectativa de vida de los clientes.
     *
     * @return List<CustomerResponseDto>.
     */
    @Override
    public List<CustomerResponseDto> getCustomersLifeExpectancy() {
        List<Customer> customers = customersRepository.findAll();
        if (customers.isEmpty()) {
            throw new BusinessException("RBEN-201-01", "Error al obtener los clientes", HttpStatus.NOT_ACCEPTABLE);
        }
        return customers.stream()
                .map(this::adapterCustomerResponseDto)
                .collect(Collectors.toList());
    }

    private CustomerResponseDto adapterCustomerResponseDto(Customer customer) {
        LocalDate lifeExpectancyDate = MapperUtil.getLocalDate(customer.getBirthDate()).plusYears(averageLifeExpectancy);
        return customerMapper.toDto(customer, lifeExpectancyDate);
    }

    /**
     * Metodo para obtener las métricas de los clientes.
     *
     * @return CustomerMetricsResponse.
     */
    @Override
    public CustomerMetricsResponse getCustomerMetrics() {
        List<Customer> customer = customersRepository.findAll();
        if (customer.isEmpty() || customer.size() < 2) { //no se puede calcular la desviación estándar con menos de 2 elementos
            throw new BusinessException("RBEN-202-01", "La lista de clientes es vacia o con menos de 2 elementos no se puede calcular la desviación estándar ", HttpStatus.NOT_ACCEPTABLE);
        }
        return new CustomerMetricsResponse(
                calculatedAverageAge(customer),
                calculatedAgeStandardDeviation(customer)
        );
    }

    /**
     * Metodo para calcular el promedio de edad de los clientes.
     *
     * @param customers
     * @return
     */
    private double calculatedAverageAge(List<Customer> customers) {
        return customers.stream()
                .mapToInt(Customer::getAge)
                .average()
                .orElse(0);
    }

    /**
     * Metodos para calcular la desviación estándar de las edades de los clientes.
     */
    private double calculatedAgeStandardDeviation(List<Customer> customerList) {
        double averageAge = calculatedAverageAge(customerList);
        double summation = customerList.stream()
                .mapToDouble(cliente -> Math.pow(cliente.getAge() - averageAge, 2))
                .sum();
        return Math.sqrt(summation / (customerList.size() - 1)); // Fórmula de desviación estándar muestral
    }
}
