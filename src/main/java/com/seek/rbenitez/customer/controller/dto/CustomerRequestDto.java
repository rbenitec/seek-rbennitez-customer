package com.seek.rbenitez.customer.controller.dto;

import com.seek.rbenitez.customer.util.ConstantUtil;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerRequestDto {
    @NotEmpty(message = ":value is required")
    private String companyName;
    @NotEmpty(message = ":value is required")
    @Size(min = 1, max = 50, message = ":length maximum is 50 characters")
    private String names;
    @NotEmpty(message = ":value is required")
    @Size(min = 1, max = 50, message = ":length maximum is 50 characters")
    private String lastName;
    @NotNull(message = ":value is required")
    @Max(value = 100, message = ":maximum is 100")
    @Min(value = 18, message = ":minimum is 18")
    private Integer age;
    @NotEmpty(message = ":value is required")
    @Size(min = 1, max = 100, message = ":length maximum is 100 characters")
    private String address;
    @NotEmpty(message = ":value is required")
    @Size(min = 8, max = 8, message = ":value is of 8 characters")
    @Pattern(regexp = ConstantUtil.PATTERN_DNI, message = ":format not valid")
    private String dni;
    @NotEmpty(message = ":value is required")
    @Size(min = 1, max = 50, message = ":length maximum is 50 characters")
    private String country;
    @NotEmpty(message = ":value is required")
    @Size(min = 9, max = 9, message = ":length is 9 characters")
    @Pattern(regexp = ConstantUtil.PATTERN_PHONE, message = ":format not valid")
    private String phone;
    @NotEmpty(message = ":value is required")
    @Size(min = 10, max = 10, message = ":length is 10 characters")
    @Pattern(regexp = ConstantUtil.PATTERN_DATE, message = ":format not valid")
    private String birthDate;
}
