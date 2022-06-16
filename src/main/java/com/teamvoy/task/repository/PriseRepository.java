package com.teamvoy.task.repository;

import com.teamvoy.task.entity.PhoneEntity;
import com.teamvoy.task.entity.PriceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface PriseRepository extends CrudRepository<PriceEntity,Long> {
    List<PriceEntity> findByPhone_Id(Long id);
}
