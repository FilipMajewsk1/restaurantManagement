package org.example.models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "Menu")
@NotNull(message = "The menu position must not be null.")

public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="menu_id")
    private int id;

    @NotNull(message = "Name must not be null.")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
    private String dishName;

    @NotNull(message = "Description must not be null.")
    @Size(min = 0, max = 150, message = "Description must be between 0 and 150 characters.")
    private String dishDescription;

    @NotNull(message = "Price must not be null.")
    @Min(value = 0, message = "The price cannot be lower than 0.")
    private int price;

    @Size(min = 0, max = 150, message = "Allergens must be between 0 and 150 characters.")
    private String allergens;

    @NotNull(message = "The menu section must not be null.")
    @Enumerated(value = EnumType.ORDINAL)
    private MenuSections section;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        org.example.models.Menu menu = (Menu) o;
        return id == menu.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
