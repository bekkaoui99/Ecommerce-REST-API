package com.hamzabekkaoui.ecommerceapplication.repository;

import com.hamzabekkaoui.ecommerceapplication.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address , Integer> {
}
