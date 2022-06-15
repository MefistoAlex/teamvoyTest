package com.teamvoy.task.model;

import com.teamvoy.task.entity.PhoneEntity;

public class StorageLine {

    private PhoneEntity phone;
    private Long count;

    public StorageLine() {
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
