package com.teamvoy.task.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "order")
    private List<StorageEntity> orderList;
    private Boolean incoming;
    private Boolean paid;

    public OrderEntity() {
        orderList = new ArrayList<>();
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<StorageEntity> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<StorageEntity> orderList) {
        this.orderList = orderList;
    }

    public Boolean getIncoming() {
        return incoming;
    }

    public void setIncoming(Boolean incoming) {
        this.incoming = incoming;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
