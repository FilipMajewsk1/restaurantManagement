package org.example.repositories;

import org.example.models.Client;
import org.springframework.data.repository.CrudRepository;


public interface ClientRepo extends CrudRepository<Client, Integer> {
}
