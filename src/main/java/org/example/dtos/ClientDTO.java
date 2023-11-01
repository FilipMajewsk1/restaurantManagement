package org.example.dtos;

import lombok.*;
import org.example.models.Client;
import org.springframework.data.domain.jaxb.SpringDataJaxb;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class ClientDTO {
    private int id;
    private String name;
    private String surname;
    private String email;

    public static ClientDTO mapToDto(Client client) {
        if (client == null) return null;

        ClientDTO dto = new ClientDTO();
        dto.id = client.getId();
        dto.name = client.getName();
        dto.surname = client.getSurname();
        dto.email = client.getEmail();

        return dto;
    }
}