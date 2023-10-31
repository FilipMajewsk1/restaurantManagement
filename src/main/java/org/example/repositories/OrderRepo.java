package org.example.repositories;

import org.example.models.Order;
import org.springframework.data.repository.CrudRepository;


public interface OrderRepo extends CrudRepository<Order, Integer> {
}
