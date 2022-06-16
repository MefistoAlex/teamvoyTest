package com.teamvoy.task.model;

import com.teamvoy.task.entity.PhoneEntity;
 //class for goods list in Order model

public class OrderLine {
    private PhoneEntity phone;
    private Long count;
    private Double price;

    public OrderLine() {
    }


    public PhoneEntity getPhone() {
        return phone;
    }

    public void setPhone(PhoneEntity phone) {
        this.phone = phone;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
