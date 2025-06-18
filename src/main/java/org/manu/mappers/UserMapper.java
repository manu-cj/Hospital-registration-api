package org.manu.mappers;

import org.manu.dto.UserRequestDTO;
import org.manu.dto.UserResponseDTO;
import org.manu.models.User;
import org.manu.models.Role;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User toEntity(UserRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setFirstname(requestDTO.getFirstName());
        user.setLastname(requestDTO.getLastName());
        user.setEmail(requestDTO.getEmail());
        return user;
    }

    /**
     * Convertit une entité User en UserResponseDTO.
     * Note: passwordHash est délibérément omis.
     */
    public UserResponseDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstname());
        dto.setLastName(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setRegistrationDate(user.getRegistrationDate());

        // Mappage des rôles (suppose que Role a une méthode getName())
        if (user.getRoles() != null) {
            dto.setRoles(user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    /**
     * Met à jour une entité User existante à partir d'un UserRequestDTO.
     * Utile pour les opérations PUT/PATCH.
     * Note: password, id, registrationDate et roles ne sont PAS mis à jour ici.
     */
    public void updateEntityFromDto(UserRequestDTO requestDTO, User user) {
        if (requestDTO == null || user == null) {
            return;
        }
        user.setUsername(requestDTO.getUsername());
        user.setFirstname(requestDTO.getFirstName());
        user.setLastname(requestDTO.getLastName());
        user.setEmail(requestDTO.getEmail());
        // Le mot de passe et les rôles doivent être gérés séparément dans le service
    }
}
