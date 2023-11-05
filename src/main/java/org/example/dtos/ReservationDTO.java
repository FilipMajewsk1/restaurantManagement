package org.example.dtos;

import lombok.*;
import org.example.models.Reservation;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private int id;
    private String ddate;
    private String hhour;
    private int client_id;
    private int table_id;
    private int guestNumber;
    private String additionalRemarks;

    public static ReservationDTO mapToDto(Reservation reservation){
        if(reservation == null) return null;

        ReservationDTO dto = new ReservationDTO();
        dto.id = reservation.getId();
        dto.ddate = reservation.getDdate();
        dto.hhour = reservation.getHhour();
        dto.client_id = reservation.getClient().getId();
        dto.table_id = reservation.getTable().getId();
        dto.guestNumber = reservation.getGuestNumber();
        dto.additionalRemarks = reservation.getAdditionalRemarks();

        return dto;
    }
}
