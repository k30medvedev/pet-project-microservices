package com.app.orderservice.repository;

import com.app.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.id = :id")
    Order findByUUID(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("DELETE from Order o where o.id=:id")
    void deleteByUUID(String id);

}
