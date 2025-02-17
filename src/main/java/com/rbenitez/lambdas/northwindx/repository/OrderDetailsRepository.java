package com.rbenitez.lambdas.northwindx.repository;

import com.rbenitez.lambdas.northwindx.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {

    @Query(value = "SELECT * FROM OrderDetails WHERE ProductID =:productId", nativeQuery = true)
    public List<OrderDetails> findByProductId(Integer productId);

    @Query(value = "SELECT * FROM OrderDetails WHERE OrderID =:orderId", nativeQuery = true)
    public List<OrderDetails> findByOrderId(Integer orderId);
}
