package com.teamvoy.task.entity;

import javax.persistence.*;

@Entity
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "phone_id")
    private PhoneEntity phone;
    private Double price;
}
