package com.teamvoy.task.repository;

import com.teamvoy.task.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity,Long> {
}
