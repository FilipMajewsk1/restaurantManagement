package org.example.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.example.dtos.OrderDTO;
import org.example.dtos.OrderLineDTO;
import org.example.models.Order;
import org.example.models.OrderLine;
import org.example.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    private final OrderRepo repository;
    private final  TableService tableService;
    private final ReservationService reservationService;
    private final MenuService menuService;
    private final Validator validator;

    @Autowired
    public OrderService(OrderRepo repository,
                        TableService tableService,
                        ReservationService reservationService,
                        MenuService menuService,
                        Validator validator){
        this.repository = repository;
        this.tableService = tableService;
        this.reservationService = reservationService;
        this.menuService = menuService;
        this.validator = validator;
    }
    public Order getOrder(int id) {
        return repository.findById(id).orElse(null);
    }

    public Iterable<Order> getAllOrders() { return repository.findAll(); }

    public Order createOrder(Order order) {
        Set<ConstraintViolation<Order>> violations = validator.validate(order);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return repository.save(order);
    }

    public Order updateOrder(int id, Order order) {
        Order orderToUpdate = repository.findById(id).orElse(null);

        if (orderToUpdate == null) {
            throw new IllegalArgumentException(String.format("The order with ID %d was not found - failed to update.", id));
        }

        Set<ConstraintViolation<Order>> violations = validator.validate(order);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        order.setId(orderToUpdate.getId());

        return repository.save(orderToUpdate);
    }

    public void deleteOrder(int id) { repository.deleteById(id); }

    public Order mapFromDto(OrderDTO dto) {
        if (dto == null) return null;

        Order order = new Order(
                -1,
                tableService.getTable(dto.getTable_id()),
                reservationService.getReservation(dto.getReservation_id()),
                new ArrayList<>()
        );

        List<OrderLineDTO> dtoLines = dto.getLines();

        if (dtoLines != null) {
            for (var dtoLine : dtoLines) {
                if (dtoLine == null) continue;

                order.getLines().add(new OrderLine(
                        -1,
                        order,
                        menuService.getPosition(dtoLine.getPosition_id()),
                        dtoLine.getQuantity()
                ));
            }
        }
        return order;
    }


}
