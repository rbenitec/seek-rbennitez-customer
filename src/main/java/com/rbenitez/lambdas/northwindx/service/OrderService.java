package com.rbenitez.lambdas.northwindx.service;

import com.rbenitez.lambdas.northwindx.controller.dto.CustomerDto;
import com.rbenitez.lambdas.northwindx.controller.dto.TransactionDto;
import com.rbenitez.lambdas.northwindx.entities.Customers;
import com.rbenitez.lambdas.northwindx.entities.OrderDetails;
import com.rbenitez.lambdas.northwindx.entities.Orders;
import com.rbenitez.lambdas.northwindx.repository.OrderRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRespository orderRespository;

    private final CustomersService customersService;

    private final OrderDetailsService orderDetailsService;

    public Optional<Orders> getAllOrderById(Integer orderId){
        return orderRespository.findById(orderId);
    }

    public List<TransactionDto> getTransactionByOrderId(Integer orderId){
        TransactionDto transactionDto = null;
        Orders orders = orderRespository.findById(orderId).get();
        Customers customer  = customersService.findCustomerById(orders.getCustomerId().trim());
        List<OrderDetails> orderDetails = orderDetailsService.getOrderDetailsByOrderId(orderId);
        // Proximo a implementar
        return null;

    }

}
