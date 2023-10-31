package org.example.models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@Table(name = "OrderLines")
@NotNull(message = "The orderLine must not be null.")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message="The line's order must not be null.")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    @NotNull(message="The orderline position must not be null.")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "menu_id")
    private Menu position;

    @Positive(message = "The orderline quantity must be positive.")
    private int quantity;

}
