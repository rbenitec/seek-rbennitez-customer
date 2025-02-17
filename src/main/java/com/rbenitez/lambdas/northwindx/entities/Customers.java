package com.rbenitez.lambdas.northwindx.entities;

import io.swagger.annotations.ApiModelProperty;
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
public class Customers {
    @Id
    private String customerID;
    @Column(name = "companyname")
    private String companyName;
    @Column(name = "contactname")
    private String contactName;
    @Column(name = "contacttitle")
    private String contactTitle;
    @Column(name = "Address")
    private String address;
    @Column(name = "City")
    private String city;
    @Column(name = "Region")
    private String region;
    @Column(name = "Postalcode")
    private String postalCode;
    @Column(name = "Country")
    private String country;
    @ApiModelProperty(position = 0)
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Fax")
    private String fax;

}
