package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.dtos.TableDTO;
import org.example.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Tag(name = "Table Controller", description = "Restaurant management API")
@RestController
public class TableController {
    private final TableService service;

    @Autowired
    public TableController(TableService service) { this.service = service; }

    @Operation(
            summary = "Create table",
            description = "Create new Table object",
            tags = { "post" })
    @PostMapping("/tables")
    public TableDTO create(@RequestBody TableDTO tableDto) {
        return TableDTO.mapToDto(service.createTable(service.mapFromDto(tableDto)));
    }

    @Operation(
            summary = "Retrieve all tables",
            description = "Get a list of all Table objects",
            tags = { "get" })
    @GetMapping("/tables")
    public List<TableDTO> getAll() {
        List<TableDTO> tables = new ArrayList<>();

        service.getAllTables().forEach((x) -> tables.add(TableDTO.mapToDto(x)));

        return tables;
    }

    @Operation(
            summary = "Retrieve table",
            description = "Get Table object by specifying its id",
            tags = { "get" })
    @GetMapping("/tables/{id}")
    public TableDTO get(@PathVariable int id) { return TableDTO.mapToDto(service.getTable(id)); }

    @Operation(
            summary = "Update table",
            description = "Update Table object by specifying its id",
            tags = { "patch" })
    @PatchMapping("/tables/{id}")
    public TableDTO update(@PathVariable int id, @RequestBody TableDTO tableDto) {
        return TableDTO.mapToDto(service.updateTable(id, service.mapFromDto(tableDto)));
    }

    @Operation(
            summary = "Delete table",
            description = "Delete Table object by specifying its id",
            tags = { "delete" })
    @DeleteMapping("/tables/{id}")
    public void delete(@PathVariable int id) { service.deleteTable(id); }

}
