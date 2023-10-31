package org.example.repositories;

import org.example.models.Reservation;
import org.springframework.data.repository.CrudRepository;


public interface ReservationRepo extends CrudRepository<Reservation, Integer> {
}