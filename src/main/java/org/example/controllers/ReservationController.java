package org.example.controllers;

import org.example.dtos.ReservationDTO;
import org.example.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class ReservationController {
    private final ReservationService service;
    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping("/reservations")
    public ReservationDTO create(@RequestBody ReservationDTO reservationDto) {
        return ReservationDTO.mapToDto(service.createReservation(service.mapFromDto(reservationDto)));
    }

    @GetMapping("/reservations")
    public List<ReservationDTO> getAll() {
        List<ReservationDTO> reservation = new ArrayList<>();

        service.getAllReservations().forEach((x) -> reservation.add(ReservationDTO.mapToDto(x)));

        return reservation;
    }

    @GetMapping("/reservations/{id}")
    public ReservationDTO get(@PathVariable int id) {
        return ReservationDTO.mapToDto(service.getReservation(id));
    }

    @PatchMapping("/reservations/{id}")
    public ReservationDTO update(@PathVariable int id, @RequestBody ReservationDTO reservationDto) {
        return ReservationDTO.mapToDto(service.updateReservation(id, service.mapFromDto(reservationDto)));
    }

    @DeleteMapping("/reservations/{id}")
    public void delete(@PathVariable int id) {
        service.deleteReservation(id);
    }
}
