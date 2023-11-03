package org.example.controllers;

import org.example.dtos.ClientDTO;
import org.example.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {
    private final ClientService service;
    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping("/reservations")
    public ClientDTO create(@RequestBody ClientDTO clientDto) {
        return ClientDTO.mapToDto(service.createClient(service.mapFromDto(clientDto)));
    }

    @GetMapping("/reservations")
    public List<ClientDTO> getAll() {
        List<ClientDTO> clients = new ArrayList<>();

        service.getAllClients().forEach((x) -> clients.add(ClientDTO.mapToDto(x)));

        return clients;
    }

    @GetMapping("/reservations/{id}")
    public ClientDTO get(@PathVariable int id) {
        return ClientDTO.mapToDto(service.getClient(id));
    }

    @PatchMapping("/reservations/{id}")
    public ClientDTO update(@PathVariable int id, @RequestBody ClientDTO clientDto) {
        return ClientDTO.mapToDto(service.updateClient(id, service.mapFromDto(clientDto)));
    }

    @DeleteMapping("/reservations/{id}")
    public void delete(@PathVariable int id) {
        service.deleteClient(id);
    }

}
