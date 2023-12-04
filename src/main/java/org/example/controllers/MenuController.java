package org.example.controllers;

import org.example.dtos.MenuDTO;
import org.example.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


import java.util.ArrayList;
import java.util.List;

@Tag(name = "Menu Controller", description = "Restaurant management API")
@RestController
public class MenuController {
    private final MenuService service;

    @Autowired
    public MenuController(MenuService service) { this.service = service; }

    @Operation(
            summary = "Create menu item",
            description = "Create new Menu object",
            tags = { "post" })
    @PostMapping("/menu")
    public MenuDTO create(@RequestBody MenuDTO menuDto) {
        return MenuDTO.mapToDto(service.createPosition(service.mapFromDto(menuDto)));
    }

    @Operation(
            summary = "Retrieve whole menu",
            description = "Get a list of all Menu objects",
            tags = { "get" })
    @GetMapping("/menu")
    public List<MenuDTO> getAll() {
        List<MenuDTO> menu = new ArrayList<>();

        service.getWholeMenu().forEach((x) -> menu.add(MenuDTO.mapToDto(x)));

        return menu;
    }

    @Operation(
            summary = "Retrieve Menu item",
            description = "Get Menu object by specifying its id",
            tags = { "get" })
    @GetMapping("/menu/{id}")
    public MenuDTO get(@PathVariable int id) { return MenuDTO.mapToDto(service.getPosition(id)); }

    @Operation(
            summary = "Update Menu item",
            description = "Update Menu object by specifying its id ",
            tags = { "patch" })
    @PatchMapping("/menu/{id}")
    public MenuDTO update(@PathVariable int id, @RequestBody MenuDTO menuDto) {
        return MenuDTO.mapToDto(service.updatePosition(id, service.mapFromDto(menuDto)));
    }

    @Operation(
            summary = "Delete Menu item",
            description = "Delete a Menu object by specifying its id",
            tags = { "delete" })
    @DeleteMapping("/menu/{id}")
    public void delete(@PathVariable int id) { service.deletePosition(id); }

}
