package com.hamzabekkaoui.ecommerceapplication.entity;


import com.hamzabekkaoui.ecommerceapplication.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    private String userId;

    private String fistName;
    private String lastName;

    private String userName;
    private String mail;
    private String password;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;


    @OneToMany(mappedBy = "user")
    List<Order> orderList;



}
