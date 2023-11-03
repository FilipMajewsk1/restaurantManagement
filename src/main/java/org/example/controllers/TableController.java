package org.example.controllers;

import org.example.dtos.TableDTO;
import org.example.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class TableController {
    private final TableService service;

    @Autowired
    public TableController(TableService service) { this.service = service; }

    @PostMapping("/tables")
    public TableDTO create(@RequestBody TableDTO tableDto) {
        return TableDTO.mapToDto(service.createTable(service.mapFromDto(tableDto)));
    }

    @GetMapping("/tables")
    public List<TableDTO> getAll() {
        List<TableDTO> tables = new ArrayList<>();

        service.getAllTables().forEach((x) -> tables.add(TableDTO.mapToDto(x)));

        return tables;
    }

    @GetMapping("/tables/{id}")
    public TableDTO get(@PathVariable int id) { return TableDTO.mapToDto(service.getTable(id)); }

    @PatchMapping("/tables/{id}")
    public TableDTO update(@PathVariable int id, @RequestBody TableDTO tableDto) {
        return TableDTO.mapToDto(service.updateTable(id, service.mapFromDto(tableDto)));
    }

    @DeleteMapping("/tables/{id}")
    public void delete(@PathVariable int id) { service.deleteTable(id); }

}
