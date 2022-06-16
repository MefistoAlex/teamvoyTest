package com.teamvoy.task.repository;

import com.teamvoy.task.entity.StorageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.math.BigDecimal;
import java.util.List;

public interface StorageRepository extends CrudRepository<StorageEntity, Long> {

    //select storage stocks
    @Query(value = "SELECT\n" +
            "\tsum(IF(incoming,count,-count)) as count, \n" +
            "\tstorage_entity.phone_id\n" +
            "FROM\n" +
            "\tstorage_entity\n" +
            "\tINNER JOIN\n" +
            "\torder_entity\n" +
            "\tON \n" +
            "\t\tstorage_entity.order_id = order_entity.id\n" +
            "GROUP BY\n" +
            "\tstorage_entity.phone_id", nativeQuery = true)
    List<Object[]> getGoodsStock();

    //select storage stock by phone
    @Query(value = "SELECT\n" +
            "\tSUM(IF(incoming,count,-count)) as count\n" +
            "FROM\n" +
            "\tstorage_entity\n" +
            "\tINNER JOIN\n" +
            "\torder_entity\n" +
            "\tON \n" +
            "\t\tstorage_entity.order_id = order_entity.id\n" +
            "WHERE\n" +
            "\tstorage_entity.phone_id = ?1", nativeQuery = true)
    BigDecimal getStockByPhone(Long phoneId);


}
