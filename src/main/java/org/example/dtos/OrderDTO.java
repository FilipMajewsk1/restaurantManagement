package org.example.dtos;

import lombok.*;
import org.example.models.Order;
import org.example.models.OrderLine;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int id;
    private String name;
    private int table_id;
    private int reservation_id;
    private List<OrderLineDTO> lines = new ArrayList<>();


    public static OrderDTO mapToDto(Order order) {
        if (order == null) return null;

        OrderDTO dto = new OrderDTO();
        dto.id = order.getId();
        dto.name = order.getName();
        dto.reservation_id = order.getReservation().getId();
        dto.table_id = order.getTable().getId();

        for (OrderLine line : order.getLines()) {
            dto.lines.add(OrderLineDTO.mapToDto(line));
        }

        return dto;
    }
}