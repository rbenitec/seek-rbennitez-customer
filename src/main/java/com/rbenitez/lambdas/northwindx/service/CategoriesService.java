package com.rbenitez.lambdas.northwindx.service;

import com.rbenitez.lambdas.northwindx.entities.Categories;
import com.rbenitez.lambdas.northwindx.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    public List<Categories> getAllCategories(){
        return categoriesRepository.findAll();
    }
}
