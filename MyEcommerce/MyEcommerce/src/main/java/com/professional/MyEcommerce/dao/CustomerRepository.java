package com.professional.MyEcommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.professional.MyEcommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String theEmail);

}
