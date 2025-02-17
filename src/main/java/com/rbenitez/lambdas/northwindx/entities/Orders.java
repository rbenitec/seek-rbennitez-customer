package com.rbenitez.lambdas.northwindx.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    private Integer orderId;
    private String customerId;
    private Integer employeeId;
    @Column(name = "Orderdate") private LocalDateTime orderDate;
    @Column(name = "Requireddate") private LocalDateTime requiredDate;
    @Column(name = "Shippeddate") private LocalDateTime shippedDate;
    @Column(name = "Shipvia") private Integer shipVia;
    private Double freight;
    @Column(name = "Shipname") private String shipName;
    @Column(name = "Shipaddress") private String shipAddress;
    @Column(name = "Shipcity") private String shipCity;
    @Column(name = "Shipregion") private String shipRegion;
    @Column(name = "Shippostalcode") private String shipPostalCode;
    @Column(name = "Shipcountry") private String shipCountry;
}
