package com.rbenitez.lambdas.northwindx.repository;

import com.rbenitez.lambdas.northwindx.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductsRepository extends JpaRepository<Products,Integer> {
}
