package com.seek.rbenitez.customer.service;

import com.seek.rbenitez.customer.controller.dto.CustomerMetricsResponse;
import com.seek.rbenitez.customer.controller.dto.CustomerRequestDto;
import com.seek.rbenitez.customer.controller.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseDto> getCustomersLifeExpectancy();

    CustomerResponseDto saveCustomer(CustomerRequestDto customer);

    CustomerMetricsResponse getCustomerMetrics();
}
