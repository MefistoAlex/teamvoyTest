package com.teamvoy.task.model;
//model for viewing storage 
import com.teamvoy.task.entity.PhoneEntity;

public class StorageLine {

    private PhoneEntity phone;
    private Long count;
    private Double price;

    public StorageLine() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
}
