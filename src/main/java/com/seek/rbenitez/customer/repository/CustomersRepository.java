package com.seek.rbenitez.customer.repository;

import com.seek.rbenitez.customer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customer,String> {
    @Query(value = "SELECT AVG(c.age) FROM customers c", nativeQuery = true)
    Double calculatedAverageAge();

    @Query(value = "SELECT STDDEV(c.age) FROM customers c", nativeQuery = true)
    Double calculatedAgeStandardDeviation();
}
