package com.teamvoy.task.model;

import com.teamvoy.task.entity.StorageEntity;
import com.teamvoy.task.entity.UserEntity;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private User user;
    private List<OrderLine> orderList;
    private Boolean incoming;
    private Double totalCost;

    public Order() {
        orderList = new ArrayList<>();
    }

    public Order(Long id, User user, Boolean incoming) {
        this.id = id;
        this.user = user;
        this.incoming = incoming;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderLine> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderLine> orderList) {
        this.orderList = orderList;
    }

    public Boolean getIncoming() {
        return incoming;
    }

    public void setIncoming(Boolean incoming) {
        this.incoming = incoming;
    }
    public void calculateTotalCost(){
        totalCost = 0D;
        for (OrderLine line:orderList){
            totalCost+=line.getPrice();
        }
    }
}
