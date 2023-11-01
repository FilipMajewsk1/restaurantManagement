package org.example.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.example.dtos.MenuDTO;
import org.example.models.Menu;
import org.example.repositories.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MenuService {
    private final MenuRepo repository;
    private final Validator validator;

    @Autowired
    public MenuService(MenuRepo repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Menu getPosition(int id){ return repository.findById(id).orElse(null); }

    public Iterable<Menu> getWholeMenu(){ return repository.findAll(); }

    public Menu createPosition(Menu position) {
        Set<ConstraintViolation<Menu>> violations = validator.validate(position);

        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return repository.save(position);
    }

    public void deletePosition(int id){ repository.deleteById(id); }

    public Menu updatePosition(int id, Menu position) {
        Menu positionToUpdate = repository.findById(id).orElse(null);

        if(positionToUpdate == null) {
            throw new IllegalArgumentException(String.format("The position with ID %d was not found - failed to update.", id));
        }

        positionToUpdate.setDishName(position.getDishName());
        positionToUpdate.setDishDescription(position.getDishDescription());
        positionToUpdate.setPrice(position.getPrice());
        positionToUpdate.setAllergens(position.getAllergens());
        positionToUpdate.setSection(position.getSection());

        Set<ConstraintViolation<Menu>> violations = validator.validate(positionToUpdate);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return repository.save(positionToUpdate);
    }

    public Menu mapFromDto(MenuDTO dto) {
        return dto != null ? new Menu(
                -1,
                dto.getDishName(),
                dto.getDishDescription(),
                dto.getPrice(),
                dto.getAllergens(),
                dto.getSection()
        ) : null;
    }
}
