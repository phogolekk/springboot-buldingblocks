package com.stacksimplify.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stacksimplify.app.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
