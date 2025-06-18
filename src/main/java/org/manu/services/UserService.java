package org.manu.services;

import lombok.AllArgsConstructor;
import org.manu.dto.UserRequestDTO;
import org.manu.dto.UserResponseDTO;
import org.manu.mappers.UserMapper;
import org.manu.models.Role;
import org.manu.models.User;
import org.manu.repositories.RoleRepository;
import org.manu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        User user = userMapper.toEntity(requestDTO);

        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setRegistrationDate(LocalDateTime.now());

        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role 'ROLE_USER' not found. Please ensure it exists."));
        user.addRole(defaultRole);

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
     }
}
