package org.example.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.example.dtos.OrderDTO;
import org.example.dtos.OrderLineDTO;
import org.example.dtos.TableDTO;
import org.example.models.Order;
import org.example.models.OrderLine;
import org.example.models.Table;
import org.example.repositories.OrderRepo;
import org.example.repositories.TableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TableService {
    private final TableRepo repository;
    private final Validator validator;

    @Autowired
    public TableService(TableRepo repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Table getTable(int id) { return repository.findById(id).orElse(null); }

    public Iterable<Table> getAllTables() { return repository.findAll(); }

    public Table createTable(Table table) {
        Set<ConstraintViolation<Table>> violations = validator.validate(table);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return repository.save(table);
    }

    public Table updateTable(int id, Table table) {
        Table tableToUpdate = repository.findById(id).orElse(null);

        if (tableToUpdate == null) {
            throw new IllegalArgumentException(String.format("The table with ID %d was not found - failed to update.", id));
        }

        Set<ConstraintViolation<Table>> violations = validator.validate(table);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        table.setId((tableToUpdate.getId()));

        return repository.save(tableToUpdate);
    }

    public void deleteTable(int id) { repository.deleteById(id); }

    public Table mapFromDto(TableDTO dto) {
        return dto != null ? new Table(
                -1,
                dto.getName(),
                dto.getSize()
        ) : null;
    }
}

