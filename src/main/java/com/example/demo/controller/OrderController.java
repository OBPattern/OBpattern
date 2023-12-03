package com.example.demo.controller;


import com.example.demo.data.entity.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> getOrders(@PathVariable("id") Long id){
        Optional<Order> order = orderService.getById(id);
        if(order.isPresent())
            return ResponseEntity.ok(order.get());
        return ResponseEntity.EMPTY;
    }

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeOrder(@PathVariable("id") Long id){
      orderService.removeOrder(id);
    }
}
