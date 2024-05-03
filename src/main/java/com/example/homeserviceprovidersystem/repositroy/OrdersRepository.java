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
    @Query("from Orders order where order.subDuty.id=:SUBDUTYID and (order.orderStatus =:SUGGESTION or order.orderStatus =:SELECTION)")
    List<Orders> findAllOrdersByOrderStatus(@Param("SUBDUTYID") Long subDutyId, @Param("SUGGESTION") OrderStatus suggestion,
            @Param("SELECTION") OrderStatus selection);
    List<Orders> findAllByOrderStatus(OrderStatus orderStatus);
}
