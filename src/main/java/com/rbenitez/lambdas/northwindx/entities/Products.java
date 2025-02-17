package com.rbenitez.lambdas.northwindx.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    @Id
    private Integer ProductID;
    @Column(name = "Productname")
    private String ProductName;
    @Column(name = "SupplierID")
    private Integer SupplierID;
    @Column(name = "CategoryID")
    private Integer CategoryID;
    @Column(name = "Quantityperunit")
    private String QuantityPerUnit;
    @Column(name = "Unitprice")
    private Double UnitPrice;
    @Column(name = "Unitsinstock")
    private Integer UnitsInStock;
    @Column(name = "Unitsonorder")
    private Integer UnitsOnOrder;
    @Column(name = "Reorderlevel")
    private Integer ReorderLevel;
    @Column(name = "Discontinued")
    private Integer Discontinued;
}
