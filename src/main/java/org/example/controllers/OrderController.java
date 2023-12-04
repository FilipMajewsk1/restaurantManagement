package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.dtos.OrderDTO;
import org.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Tag(name = "Order Controller", description = "Restaurant management API")
@RestController
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) { this.service = service; }

    @Operation(
            summary = "Create order",
            description = "Create new Order object",
            tags = { "post" })
    @PostMapping("/orders")
    public OrderDTO create(@RequestBody OrderDTO orderDto) {
        return OrderDTO.mapToDto((service.createOrder(service.mapFromDto(orderDto))));
    }

    @Operation(
            summary = "Retrieve all orders",
            description = "Get a list of all Order objects",
            tags = { "get" })
    @GetMapping("/orders")
    public List<OrderDTO> getAll() {
        List<OrderDTO> orders = new ArrayList<>();

        service.getAllOrders().forEach((x) -> orders.add(OrderDTO.mapToDto(x)));

        return orders;
    }

    @Operation(
            summary = "Retrieve order",
            description = "Get Order object by specifying its id",
            tags = { "get" })
    @GetMapping("/orders/{id}")
    public OrderDTO get(@PathVariable int id) { return OrderDTO.mapToDto(service.getOrder(id));}


    @Operation(
            summary = "Update order",
            description = "Update Order object by specifying its id",
            tags = { "patch" })
    @PatchMapping("/orders/{id}")
    public OrderDTO update(@PathVariable int id, @RequestBody OrderDTO orderDto) {
        return OrderDTO.mapToDto(service.updateOrder(id, service.mapFromDto(orderDto)));
    }

    @Operation(
            summary = "Delete order",
            description = "Delete Order object by specifying its id",
            tags = { "delete" })
    @DeleteMapping("/orders/{id}")
    public void delete(@PathVariable int id) {
        service.deleteOrder(id);
    }
}


