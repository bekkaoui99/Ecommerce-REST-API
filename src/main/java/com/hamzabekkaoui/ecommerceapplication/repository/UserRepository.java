package com.hamzabekkaoui.ecommerceapplication.repository;

import com.hamzabekkaoui.ecommerceapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , String> {

    Optional<User> findByMail(String mail);
    Optional<User> findByUserName(String mail);
    boolean existsUserByMail(String mail);
}
