package org.example.repositories;

import org.example.models.Menu;
import org.springframework.data.repository.CrudRepository;


public interface MenuRepo extends CrudRepository<Menu, Integer> {
}