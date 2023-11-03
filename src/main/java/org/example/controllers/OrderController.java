package org.example.controllers;

import org.example.dtos.OrderDTO;
import org.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) { this.service = service; }

    @PostMapping("/orders")
    public OrderDTO create(@RequestBody OrderDTO orderDto) {
        return OrderDTO.mapToDto((service.createOrder(service.mapFromDto(orderDto))));
    }

    @GetMapping("/orders")
    public List<OrderDTO> getAll() {
        List<OrderDTO> orders = new ArrayList<>();

        service.getAllOrders().forEach((x) -> orders.add(OrderDTO.mapToDto(x)));

        return orders;
    }

    @GetMapping("/orders/{id}")
    public OrderDTO get(@PathVariable int id) { return OrderDTO.mapToDto(service.getOrder(id));}


    @PatchMapping("/orders/{id}")
    public OrderDTO update(@PathVariable int id, @RequestBody OrderDTO orderDto) {
        return OrderDTO.mapToDto(service.updateOrder(id, service.mapFromDto(orderDto)));
    }

    @DeleteMapping("/orders/{id}")
    public void delete(@PathVariable int id) {
        service.deleteOrder(id);
    }
}


