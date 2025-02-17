package com.rbenitez.lambdas.northwindx.repository;

import com.rbenitez.lambdas.northwindx.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRespository extends JpaRepository<Orders, Integer> {
}
