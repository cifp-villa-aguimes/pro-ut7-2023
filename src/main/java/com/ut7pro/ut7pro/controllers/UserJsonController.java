/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ut7pro.ut7pro.controllers;

import com.ut7pro.ut7pro.models.UserJson;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usersJson")
public class UserJsonController {

    @GetMapping
    @ResponseBody
    public List<UserJson> getUsers(@RequestParam(value = "roles", required = false) List<String> roles,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "edad", required = false, defaultValue = "0") int edad,
            @RequestParam(value = "sortBy", required = false) String sortBy) {

        // Crea una lista de usuarios
        List<String> userNames = List.of("John Doe", "Jane Smith", "Michael Johnson", "Emily Davis", "Robert Brown");
        List<String> domains = List.of("example.com", "test.com", "domain.com");
        List<String> userRoles = List.of("Administrador", "Editor", "Usuario Registrado", "Invitado");

        List<UserJson> users = new ArrayList<>();

        Random random = new Random();

        for (int i = 1; i <= 15; i++) {

            String userName = userNames.get(random.nextInt(userNames.size()));
            String userEmail = userName.toLowerCase().replaceAll(" ", "") + "@" + domains.get(random.nextInt(domains.size()));
            int userAge = random.nextInt(71) + 15; // Genera un número aleatorio entre 15 y 85
            String userRole = userRoles.get(random.nextInt(userRoles.size()));

            users.add(new UserJson(i, userName, userEmail, userAge, userRole));
        }

        // Revisar RequestParam:
        System.out.println("Valores de los parámetros:");
        System.out.println("Roles: " + roles);
        System.out.println("Name: " + name);
        System.out.println("Edad: " + edad);
        System.out.println("SortBy: " + sortBy);

        // Filtrar la lista de usuarios basándote en los parámetros de entrada
        if (roles != null && !roles.isEmpty()) {
            users = users.stream()
                    .filter(user -> roles.contains(user.getRol()))
                    .collect(Collectors.toList());
        }
        if (name != null) {
            users = users.stream()
                    .filter(user -> user.getName().contains(name))
                    .collect(Collectors.toList());
        }
        if (edad != 0) {
            users = users.stream()
                    .filter(user -> user.getEdad() >= edad)
                    .collect(Collectors.toList());
        }

        // Ordenar la lista de usuarios basándote en el parámetro "sortBy" --> "asc" o "desc"
        if (sortBy != null) {
            if (sortBy.equals("asc")) {
                users = users.stream()
                        .sorted(Comparator.comparing(UserJson::getEdad))
                        .collect(Collectors.toList());
            } else if (sortBy.equals("desc")) {
                users = users.stream()
                        .sorted(Comparator.comparing(UserJson::getEdad).reversed())
                        .collect(Collectors.toList());
            }
        }

        // Return
        return users;
    }
}
