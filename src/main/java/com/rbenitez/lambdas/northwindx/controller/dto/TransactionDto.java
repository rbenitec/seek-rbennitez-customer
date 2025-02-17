package com.rbenitez.lambdas.northwindx.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rbenitez.lambdas.northwindx.entities.OrderDetails;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {
    private Integer orderId;
    private CustomerDto customer;
    private Integer employeeID;
    private LocalDateTime orderDate;
    private LocalDateTime requiredDate;
    private LocalDateTime shippedDate;
    private List<OrderDetailsDto> orderDetails;
    private Integer shipVia;
    private Double freight;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    private String shipPostalCode;
    private String shipCountry;
}
