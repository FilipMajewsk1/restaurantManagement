package org.example.repositories;

import org.example.models.Table;
import org.springframework.data.repository.CrudRepository;


public interface TableRepo extends CrudRepository<Table, Integer> {
}