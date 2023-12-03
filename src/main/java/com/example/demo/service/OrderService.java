package com.example.demo.service;

import com.example.demo.data.db.OrderEvtStoreRepository;
import com.example.demo.data.db.OrderRepository;
import com.example.demo.data.entity.Order;
import com.example.demo.data.entity.OrderEventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderEvtStoreRepository orderEvtStoreRepository;


    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Optional<Order> getById(Long id){

        return orderRepository.findById(id);
    }

    //TODO: make @Transactional and test rollback
    public void removeOrder(Long id){
        //TODO: implement async
        Optional<Order> orderDel = orderRepository.findById(id);
        if(orderDel.isPresent()) {
            orderRepository.deleteById(id);

            String oderDetails = orderDel.get().getProductName() + " " + orderDel.get().getOrderStatus();

            OrderEventStore orderEventStore = new OrderEventStore(id, oderDetails, "DELETE", orderDel.get().getCreatedDate(), Timestamp.valueOf(LocalDateTime.now()));
            orderEvtStoreRepository.save(orderEventStore);
        }
        else{
            //TODO: add custom exception
            throw new RuntimeException("ID NOT PRESENT");
        }
    }

    //TODO: make @Transactional and test rollback
    public Order createOrder(Order order) {
        //TODO: implement async
        if(order.getId()==null){
            order.setId(Long.valueOf(UUID.randomUUID().toString()));
        }
        Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());
        order.setCreatedDate(createdDate);
        order.setModifiedDate(createdDate);

        Order orderSave=orderRepository.save(order);
        String detail=order.getProductName()+" "+order.getOrderStatus();
        OrderEventStore orderEventStore = new OrderEventStore(order.getId(),detail,"ADD",createdDate,createdDate);

        return orderSave;
    }
}
