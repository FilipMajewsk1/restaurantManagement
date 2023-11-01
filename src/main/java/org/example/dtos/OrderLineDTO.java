package org.example.dtos;

import lombok.*;
import org.example.models.OrderLine;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class OrderLineDTO {
    private int id;
    private int position_id;
    private int quantity;

    public static OrderLineDTO mapToDto(OrderLine line) {
        OrderLineDTO dto = new OrderLineDTO();
        dto.id = line.getId();
        dto.position_id = line.getPosition().getId();
        dto.quantity = line.getQuantity();

        return dto;
    }
}