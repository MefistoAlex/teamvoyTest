package com.teamvoy.task.service;

import com.teamvoy.task.entity.OrderEntity;
import com.teamvoy.task.entity.PhoneEntity;
import com.teamvoy.task.entity.PriceEntity;
import com.teamvoy.task.entity.StorageEntity;
import com.teamvoy.task.model.Order;
import com.teamvoy.task.model.OrderLine;
import com.teamvoy.task.model.StorageLine;
import com.teamvoy.task.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerService {
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private PriseRepository priceRepository;

    public PhoneEntity addPhone(PhoneEntity phone) {
        //adding phone to DB
        return phoneRepository.save(phone);
    }

    public OrderEntity incomingOrder(Order order) {
        //test order
        /* order.setIncoming(true);
        order.setUser(User.toModel(userRepository.findById(2L).get()));
        OrderLine orderLine = new OrderLine(phoneRepository.findById(3L).get(),4L,4.12D );
        OrderLine orderLine2 = new OrderLine(phoneRepository.findById(4L).get(),13L,23.12D );
        order.getOrderList().add(orderLine);
        order.getOrderList().add(orderLine2);*/
        OrderEntity entity = toEntityOrder(order);
        return orderRepository.save(entity);
    }

    public OrderEntity toEntityOrder(Order order) {
        //convert OrderModel to OrderEntity
        OrderEntity entity = new OrderEntity();
        entity.setUser(userRepository.findById(order.getUser().getId()).get());
        entity.setIncoming(order.getIncoming());
        for (OrderLine line : order.getOrderList()) {
            StorageEntity storage = new StorageEntity();
            storage.setOrder(entity);
            storage.setPhone(line.getPhone());
            storage.setCount(line.getCount());
            entity.getOrderList().add(storage);
        }
        return entity;
    }

    public List<StorageLine> getStorageStock() {
        List<StorageLine> storageLines = new ArrayList<>();
        List<Object[]> list = storageRepository.find();
        for (Object[] a : list) {
            StorageLine line = new StorageLine();
            BigInteger count = (BigInteger) a[0];
            BigInteger id = (BigInteger) a[1];
            line.setCount(count.longValue());
            line.setPhone(phoneRepository.findById(id.longValue()).get());
            line.setPrice(priceRepository.findTopByPhone(line.getPhone()).getPrice());
            storageLines.add(line);
        }
        return storageLines;
    }

    public List<PriceEntity> setPrices(List<PriceEntity> priceList) {
//        priceList = new ArrayList<>();
//        PriceEntity entity1 = new PriceEntity();
//        entity1.setPhone(phoneRepository.findById(3L).get());
//        entity1.setPrice(10D);
//        PriceEntity entity = new PriceEntity();
//        entity.setPhone(phoneRepository.findById(4L).get());
//        entity.setPrice(20D);
//        PriceEntity entity2 = new PriceEntity();
//        entity2.setPhone(phoneRepository.findById(5L).get());
//        entity2.setPrice(30D);
//
//        priceList.add(entity1);
//        priceList.add(entity);
//        priceList.add(entity2);

       return (List<PriceEntity>) priceRepository.saveAll(priceList);
    }
}
