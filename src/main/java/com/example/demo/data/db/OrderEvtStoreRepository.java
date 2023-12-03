package com.example.demo.data.db;

import com.example.demo.data.entity.Order;
import com.example.demo.data.entity.OrderEventStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEvtStoreRepository extends JpaRepository<OrderEventStore, Long> {
}
