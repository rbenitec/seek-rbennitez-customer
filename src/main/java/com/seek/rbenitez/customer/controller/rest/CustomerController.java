package com.seek.rbenitez.customer.controller.rest;

import com.seek.rbenitez.customer.controller.dto.CustomerMetricsResponse;
import com.seek.rbenitez.customer.controller.dto.CustomerRequestDto;
import com.seek.rbenitez.customer.controller.dto.CustomerResponseDto;
import com.seek.rbenitez.customer.entities.Customer;
import com.seek.rbenitez.customer.service.CustomerService;
import com.seek.rbenitez.customer.util.ResponseMessage;
import com.seek.rbenitez.customer.util.WrapperResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/seek/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/life-expectancy")
    public ResponseEntity<WrapperResponse<List<CustomerResponseDto>>> getAllCustomer() {
        return new WrapperResponse<>(
                true,
                ResponseMessage.SUCCESS_MESSAGE,
                customerService.getCustomersLifeExpectancy()).createResponse();
    }

    @PostMapping()
    public ResponseEntity<WrapperResponse<CustomerResponseDto>> saveCustomer(@RequestBody @Valid CustomerRequestDto customerRequestDto) {
        return new WrapperResponse<>(
                true,
                ResponseMessage.SUCCESS_MESSAGE,
                customerService.saveCustomer(customerRequestDto)).createResponseCreated();
    }

    @GetMapping("/metrics")
    public ResponseEntity<WrapperResponse<CustomerMetricsResponse>> getCustomerMetrics() {
        return new WrapperResponse<>(
                true,
                ResponseMessage.SUCCESS_MESSAGE,
                customerService.getCustomerMetrics()).createResponse();
    }

    /*
    @GetMapping("/contact")
    public List<Customer> findByContactTitle(@Param("contactTitle") String contactTitle) {
        log.info("Call all customers with contact title: {}", contactTitle);
        return customerService.findByContactTitle(contactTitle);
    }

    @GetMapping("/quantities/{header}")
    public Map<Object, Long> findCustomerByHeader(@PathVariable("header") String header) {
        log.info("Find quantities by: {}", header);
        return customerService.findCustomerByHeader(header);
    }

     */

}
