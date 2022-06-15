package com.teamvoy.task.repository;

import com.teamvoy.task.entity.PhoneEntity;
import com.teamvoy.task.entity.PriceEntity;
import org.springframework.data.repository.CrudRepository;

public interface PriseRepository extends CrudRepository<PriceEntity,Long> {
    PriceEntity findTopByPhone(PhoneEntity phone);
}
