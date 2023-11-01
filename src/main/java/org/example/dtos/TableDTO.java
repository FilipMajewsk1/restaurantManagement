package org.example.dtos;

import lombok.*;
import org.example.models.Table;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableDTO {

    private int id;
    private String name;
    private int size;

    public static TableDTO mapToDto(Table table){
        if( table == null ) return null;

        TableDTO dto = new TableDTO();
        dto.id = table.getId();
        dto.name = table.getName();
        dto.size = table.getSize();

        return dto;
    }
}
