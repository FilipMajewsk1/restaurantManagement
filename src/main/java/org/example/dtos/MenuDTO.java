package org.example.dtos;
import lombok.*;
import org.example.models.Menu;
import org.example.models.MenuSections;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    private int id;

    private String dishName;

    private String dishDescription;

    private int price;

    private String allergens;

    private MenuSections section;

    public static MenuDTO mapToDto(Menu menu){
        if(menu == null) return null;

        MenuDTO dto = new MenuDTO();
        dto.id = menu.getId();
        dto.dishName = menu.getDishName();
        dto.dishDescription = menu.getDishDescription();
        dto.price = menu.getPrice();
        dto.allergens = menu.getAllergens();
        dto.section = menu.getSection();

        return dto;
    }
}
