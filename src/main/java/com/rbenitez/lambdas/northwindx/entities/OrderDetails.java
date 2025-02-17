package com.rbenitez.lambdas.northwindx.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(value = CompoundOrderDetails.class)
public class OrderDetails {
    @Id
    private Integer orderID;
    @Id
    private Integer productID;
    @Column(name = "Unitprice")
    private Double unitPrice;
    private Integer quantity;
    private Double discount;

}
