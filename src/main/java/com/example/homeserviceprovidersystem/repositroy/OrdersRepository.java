package com.example.homeserviceprovidersystem.repositroy;

import com.example.homeserviceprovidersystem.entity.Orders;
import com.example.homeserviceprovidersystem.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query("from Orders order where order.expert.id=:ID and order.orderStatus =:ORDERSTATUS")
    List<Orders> findAllOrdersByOrderStatus(@Param("ID") Long expertId, @Param("ORDERSTATUS") OrderStatus orderStatus);
}
