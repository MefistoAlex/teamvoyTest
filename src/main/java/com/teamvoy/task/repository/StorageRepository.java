package com.teamvoy.task.repository;

import com.teamvoy.task.entity.StorageEntity;
import com.teamvoy.task.model.OrderLine;
import com.teamvoy.task.model.StorageLine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.sql.ResultSet;
import java.util.List;

public interface StorageRepository extends CrudRepository<StorageEntity,Long> {


    @Query("select count (distinct s) from StorageEntity s where s.order.incoming = ?1")
    long countDistinctByOrder_Incoming(Boolean incoming);

    @Query("select s from StorageEntity s where s.phone.id = ?1")
    List<StorageEntity> findByPhone_Id(Long id);

    @Query("select s from StorageEntity s where s.order.incoming = ?1")
    List<StorageEntity> findByOrder_Incoming(Boolean incoming);

    @Query(value = "SELECT DISTINCT " +
            "storage_entity.count AS count, " +
            "storage_entity.phone_id AS id " +
            "FROM " +
            "storage_entity " +
            "INNER JOIN " +
            "phone_entity " +
            "ON " +
            "storage_entity.phone_id = phone_entity.id",nativeQuery = true)
    List<Object[]> find();



}
