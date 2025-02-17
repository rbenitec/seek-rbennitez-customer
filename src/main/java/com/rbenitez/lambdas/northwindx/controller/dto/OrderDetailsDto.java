package com.rbenitez.lambdas.northwindx.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailsDto {
    private Integer orderID;
    private Integer productID;
    private Double unitPrice;
    private Integer quantity;
    private Double discount;
}
