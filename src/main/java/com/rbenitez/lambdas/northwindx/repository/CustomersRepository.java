package com.rbenitez.lambdas.northwindx.repository;

import com.rbenitez.lambdas.northwindx.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomersRepository extends JpaRepository<Customers,String> {
}
