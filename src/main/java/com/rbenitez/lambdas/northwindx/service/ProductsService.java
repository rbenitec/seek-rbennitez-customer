package com.rbenitez.lambdas.northwindx.service;

import com.rbenitez.lambdas.northwindx.entities.Products;
import com.rbenitez.lambdas.northwindx.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;

    public List<Products> getProducts (){
        return productsRepository.findAll();
    }

}
