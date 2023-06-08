/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.ut7pro.ut7pro.services;

import com.ut7pro.ut7pro.exceptions.NotFoundException;
import com.ut7pro.ut7pro.models.User;
import com.ut7pro.ut7pro.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User updateUser(User updatedUser, Long id) {
        // Verificar si el usuario existe en la base de datos
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found with ID: " + id);
        }
        // Obtener el usuario existente
        User existingUser = optionalUser.get();

        // Aplicar las actualizaciones en el usuario existente
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setToken(updatedUser.getToken());
        existingUser.setRolId(updatedUser.getRolId());

        // Guardar los cambios en la base de datos
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        // Verificar si el usuario existe en la base de datos
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
