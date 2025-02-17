package com.rbenitez.lambdas.northwindx.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categories {
    @Id
    @Column(name = "CategoryID")
    private Integer categoryId;
    @Column(name = "Categoryname")
    private String categoryName;
    private String description;
}
