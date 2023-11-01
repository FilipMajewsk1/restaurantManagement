package org.example.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.example.dtos.ClientDTO;
import org.example.models.Client;
import org.example.repositories.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ClientService {
    private final ClientRepo repository;
    private final Validator validator;

    @Autowired
    public ClientService(ClientRepo repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Client getClient(int id) {
        return repository.findById(id).orElse(null);
    }

    public Iterable<Client> getAllClients() {
        return repository.findAll();
    }

    public Client createClient(Client client) {
        Set<ConstraintViolation<Client>> violations = validator.validate(client);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return repository.save(client);
    }

    public void deleteClient(int id) {
        repository.deleteById(id);
    }

    public Client updateClient(int id, Client client) {
        Client clientToUpdate = repository.findById(id).orElse(null);

        if (clientToUpdate == null) {
            throw new IllegalArgumentException(String.format("The client with ID %d was not found - failed to update.", id));
        }

        clientToUpdate.setName(client.getName());
        clientToUpdate.setSurname(client.getSurname());
        clientToUpdate.setEmail(client.getEmail());
        clientToUpdate.setPhoneNum(client.getPhoneNum());

        Set<ConstraintViolation<Client>> violations = validator.validate(clientToUpdate);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return repository.save(clientToUpdate);
    }

    public Client mapFromDto(ClientDTO dto) {
        return dto != null ? new Client(
                -1,
                dto.getName(),
                dto.getSurname(),
                dto.getEmail(),
                dto.getPhoneNum()
        ) : null;
    }
}