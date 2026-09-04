package com.salaoAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salaoAPI.Entidades.OrderItem;
import com.salaoAPI.Entidades.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}
