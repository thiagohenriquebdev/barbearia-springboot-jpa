package com.salaoAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salaoAPI.Entidades.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
