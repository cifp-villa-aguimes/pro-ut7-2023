/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ut7pro.ut7pro.controllers;

import com.ut7pro.ut7pro.models.UserJson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usersJson")
public class UserJsonController {
    @GetMapping
    @ResponseBody
    public List<UserJson> getUsers() {
        // Crea una lista de usuarios
        List<UserJson> users = new ArrayList<>();
        users.add(new UserJson(1, "John Doe", "john@example.com"));
        users.add(new UserJson(2, "Jane Smith", "jane@example.com"));

        return users;
    }
}
