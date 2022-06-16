package com.teamvoy.task.model;

import java.util.ArrayList;
import java.util.List;
//model to order goods by user of adding goods by manager
public class Order {
    private Long id;
    private User user;
    private List<OrderLine> orderList;
    private Boolean incoming;
    private Double totalCost;

    public Order() {
        orderList = new ArrayList<>();
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

    public void calculateTotalCost() {
        totalCost = 0D;
        for (OrderLine line : orderList) {
            totalCost += line.getPrice();
        }
    }
}
