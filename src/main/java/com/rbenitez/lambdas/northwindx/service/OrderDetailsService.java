package com.rbenitez.lambdas.northwindx.service;

import com.rbenitez.lambdas.northwindx.entities.OrderDetails;
import com.rbenitez.lambdas.northwindx.repository.OrderDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;

    public List<OrderDetails> getOrderDetailsByProductId(Integer productId){
        return orderDetailsRepository.findByProductId(productId);
    }

    public List<OrderDetails> getOrderDetailsByOrderId(Integer orderId){
        return orderDetailsRepository.findByOrderId(orderId);
    }

}
