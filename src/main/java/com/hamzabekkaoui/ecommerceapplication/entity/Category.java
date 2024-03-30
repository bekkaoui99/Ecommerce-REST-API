package com.hamzabekkaoui.ecommerceapplication.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    private String categoryName;

    private String description;

    @OneToMany(mappedBy = "category")
    List<Product> products;

}
