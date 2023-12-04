package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.dtos.ReservationDTO;
import org.example.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Tag(name = "Reservation Controller", description = "Restaurant management API")
@RestController
public class ReservationController {
    private final ReservationService service;
    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create reservation",
            description = "Create new Reservation object",
            tags = { "post" })
    @PostMapping("/reservations")
    public ReservationDTO create(@RequestBody ReservationDTO reservationDto) {
        return ReservationDTO.mapToDto(service.createReservation(service.mapFromDto(reservationDto)));
    }

    @Operation(
            summary = "Retrieve all reservations",
            description = "Get a list of all Reservation objects",
            tags = { "get" })
    @GetMapping("/reservations")
    public List<ReservationDTO> getAll() {
        List<ReservationDTO> reservation = new ArrayList<>();

        service.getAllReservations().forEach((x) -> reservation.add(ReservationDTO.mapToDto(x)));

        return reservation;
    }

    @Operation(
            summary = "Retrieve reservation",
            description = "Get Reservation object by specifying its id",
            tags = { "get" })
    @GetMapping("/reservations/{id}")
    public ReservationDTO get(@PathVariable int id) {
        return ReservationDTO.mapToDto(service.getReservation(id));
    }

    @Operation(
            summary = "Update reservation",
            description = "Update Reservation object by specifying its id",
            tags = { "patch" })
    @PatchMapping("/reservations/{id}")
    public ReservationDTO update(@PathVariable int id, @RequestBody ReservationDTO reservationDto) {
        return ReservationDTO.mapToDto(service.updateReservation(id, service.mapFromDto(reservationDto)));
    }

    @Operation(
            summary = "Delete reservation",
            description = "Delete Reservation object by specifying its id",
            tags = { "delete" })
    @DeleteMapping("/reservations/{id}")
    public void delete(@PathVariable int id) {
        service.deleteReservation(id);
    }
}
