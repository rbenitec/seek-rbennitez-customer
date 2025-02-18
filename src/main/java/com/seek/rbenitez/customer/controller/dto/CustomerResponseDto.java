package com.seek.rbenitez.customer.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponseDto {
    private Integer customerId;
    private String companyName;
    private String lastName;
    private Integer age;
    private String names;
    private String address;
    private String country;
    private String phone;
    private String birthdate;
    private String createdAt;
    private LocalDate dateLifeExpectancy;
}
