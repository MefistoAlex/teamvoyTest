package com.teamvoy.task.controller;

import com.teamvoy.task.entity.PhoneEntity;
import com.teamvoy.task.entity.UserEntity;
import com.teamvoy.task.exception.UserAlreadyExistException;
import com.teamvoy.task.service.ManagerService;
import com.teamvoy.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @PostMapping
    public ResponseEntity addPhone(@RequestBody PhoneEntity phone) {
        try {
            managerService.addPhone(phone);
            return ResponseEntity.ok().body("Phone added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
