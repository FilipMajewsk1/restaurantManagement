package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@jakarta.persistence.Table(name = "Tables")
@NotNull(message = "The table must not be null.")

public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="table_id")
    private int id;

    @NotNull(message = "Name must not be null.")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
    private String name;

    @NotNull(message = "Size must not be null.")
    @Min(value = 1, message = "The table size cannot be lower than 1.")
    private int size;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return id == table.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
