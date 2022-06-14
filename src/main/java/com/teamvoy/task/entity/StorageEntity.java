package com.teamvoy.task.entity;

import javax.persistence.*;

@Entity
public class StorageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name  = "order_id")
    private OrderEntity order;
    @OneToOne
    @JoinColumn(name = "phone_id")
    private PhoneEntity phone;
    private Long count;
}

