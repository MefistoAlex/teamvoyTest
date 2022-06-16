package com.teamvoy.task.service;

import com.teamvoy.task.entity.OrderEntity;
import com.teamvoy.task.repository.OrderRepository;

//thread to incoming order
public class IncomingOrderThread extends Thread {
    public static final long TIME_TO_WAIT = 600000;
    OrderRepository orderRepository;
    OrderEntity order;

    public IncomingOrderThread(OrderRepository orderRepository, OrderEntity order) {
        this.orderRepository = orderRepository;
        this.order = order;
    }

    public void run() {
        try {
            orderRepository.save(order);
            this.sleep(TIME_TO_WAIT);//waiting for payment
            OrderEntity entity = orderRepository.findById(order.getId()).get();
            if (!entity.getPaid()) {
                orderRepository.delete(entity);//if dosent paid - delete order
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
