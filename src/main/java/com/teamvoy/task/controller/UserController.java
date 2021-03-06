package com.teamvoy.task.controller;

import com.teamvoy.task.entity.UserEntity;
import com.teamvoy.task.exception.OutOfStockException;
import com.teamvoy.task.exception.UserAlreadyExistException;
import com.teamvoy.task.model.Order;
import com.teamvoy.task.service.ManagerService;
import com.teamvoy.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//REST controller for users
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ManagerService managerService;

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody UserEntity user) {
        // user registration
        try {
            userService.registration(user);
            return ResponseEntity.ok().body("User added successfully");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/storage")
    public ResponseEntity getStorageStock() {
        // viewing storage stocks with actual prices
        try {
            return ResponseEntity.ok().body(managerService.getStorageStock());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/order")
    public ResponseEntity outgoingOrder(@RequestBody Order order) {
        //order goods for users
        try {
            userService.outgoingOrder(order);
            return ResponseEntity.ok().body("Incoming order added successfully");
        } catch (OutOfStockException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/pay")
    public ResponseEntity payOrder(@RequestParam Long order_id) {
        //paying order for users
        try {
            userService.payOrder(order_id);
            return ResponseEntity.ok().body("Incoming order added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}


