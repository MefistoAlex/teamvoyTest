package com.teamvoy.task.controller;

import com.teamvoy.task.entity.PhoneEntity;
import com.teamvoy.task.entity.PriceEntity;
import com.teamvoy.task.model.Order;
import com.teamvoy.task.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @PostMapping("/add_phone")
    public ResponseEntity addPhone(@RequestBody PhoneEntity phone) {
        try {
            managerService.addPhone(phone);
            return ResponseEntity.ok().body("Phone added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/price")
    public ResponseEntity setPrices(@RequestBody List<PriceEntity> priceList) {
        try {
            managerService.setPrices(priceList);
            return ResponseEntity.ok().body("Prises are set");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/order")
    public ResponseEntity incomingOrder(@RequestBody Order order) {
        try {
            managerService.incomingOrder(order);
            return ResponseEntity.ok().body("Incoming order added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/storage")
    public ResponseEntity getStorageStock(){
        try {
            return ResponseEntity.ok().body(managerService.getStorageStock());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
