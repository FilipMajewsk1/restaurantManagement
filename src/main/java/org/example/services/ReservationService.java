package org.example.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.example.dtos.OrderDTO;
import org.example.dtos.OrderLineDTO;
import org.example.dtos.ReservationDTO;
import org.example.models.Order;
import org.example.models.OrderLine;
import org.example.models.Reservation;
import org.example.repositories.OrderRepo;
import org.example.repositories.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ReservationService {
    private final ReservationRepo repository;
    private final ClientService clientService;
    private final TableService tableService;
    private final Validator validator;

    @Autowired
    public ReservationService(ReservationRepo repository,
                              ClientService clientService,
                              TableService tableService,
                              Validator validator){
        this.repository = repository;
        this.clientService = clientService;
        this.tableService = tableService;
        this.validator = validator;
    }

    public Reservation getReservation(int id) { return  repository.findById(id).orElse(null); }

    public Iterable<Reservation> getAllReservations() { return repository.findAll(); }

    public Reservation createReservation(Reservation reservation) {
        Set<ConstraintViolation<Reservation>> violations = validator.validate(reservation);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return repository.save(reservation);
    }

    public Reservation updateReservation(int id, Reservation reservation) {
        Reservation reservationToUpdate = repository.findById(id).orElse(null);

        if (reservationToUpdate == null) {
            throw new IllegalArgumentException(String.format("The reservation with ID %d was not found - failed to update.", id));
        }

        Set<ConstraintViolation<Reservation>> violations = validator.validate(reservation);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        reservation.setId(reservation.getId());

        return repository.save(reservationToUpdate);
    }

    public void deleteReservation(int id) { repository.deleteById(id); }

    public Reservation mapFromDto(ReservationDTO dto) {
        return dto != null ? new Reservation(
                -1,
                dto.getDdate(),
                dto.getHhour(),
                clientService.getClient(dto.getClient_id()),
                tableService.getTable(dto.getTable_id()),
                dto.getGuestNumber(),
                dto.getAdditionalRemarks()
        ): null;
    }
}
