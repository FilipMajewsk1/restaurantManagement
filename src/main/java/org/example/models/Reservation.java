package org.example.models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "Reservations")
@NotNull(message = "The reservation must not be null.")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservation_id")
    private int id;

    @NotNull(message = "Date must not be null.")
    @Size(min = 2, max = 50, message = "Date must be between 2 and 50 characters.")
    private String date;

    @NotNull(message = "Hour must not be null.")
    @Size(min = 1, max = 10, message = "Hour must be between 1 and 10 characters.")
    private String hour;

    @NotNull(message = "The client must not be null.")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;

    @NotNull(message = "The table must not be null.")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "table_id", referencedColumnName = "table_id")
    private org.example.models.Table table;

    @NotNull(message = "The number must not be null.")
    @Min(value = 1, message = "The number cannot be lower than 1.")
    private int guestNumber;

    @NotNull(message = "Remarks must not be null.")
    @Size(min = 0, max = 150, message = "Remarks must be between 0 and 150 characters.")
    private String additionalRemarks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation reservation = (Reservation) o;
        return id == reservation.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
