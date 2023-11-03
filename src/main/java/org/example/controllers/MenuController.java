package org.example.controllers;

import org.example.dtos.MenuDTO;
import org.example.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuController {
    private final MenuService service;

    @Autowired
    public MenuController(MenuService service) { this.service = service; }

    @PostMapping("/tables")
    public MenuDTO create(@RequestBody MenuDTO menuDto) {
        return MenuDTO.mapToDto(service.createPosition(service.mapFromDto(menuDto)));
    }

    @GetMapping("/tables")
    public List<MenuDTO> getAll() {
        List<MenuDTO> menu = new ArrayList<>();

        service.getWholeMenu().forEach((x) -> menu.add(MenuDTO.mapToDto(x)));

        return menu;
    }

    @GetMapping("/tables/{id}")
    public MenuDTO get(@PathVariable int id) { return MenuDTO.mapToDto(service.getPosition(id)); }

    @PatchMapping("/tables/{id}")
    public MenuDTO update(@PathVariable int id, @RequestBody MenuDTO menuDto) {
        return MenuDTO.mapToDto(service.updatePosition(id, service.mapFromDto(menuDto)));
    }

    @DeleteMapping("/tables/{id}")
    public void delete(@PathVariable int id) { service.deletePosition(id); }

}
