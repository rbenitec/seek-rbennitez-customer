package com.rbenitez.lambdas.northwindx.repository;

import com.rbenitez.lambdas.northwindx.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
}
