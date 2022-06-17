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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerService {
    //Repositories
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
        //adding goods and setting prises for manager
        //get entity order from model
        OrderEntity entity = toEntityOrder(order);
        //getting price list  from order and save it to DB
        priceRepository.saveAll(getPriceList(order));
        //save order to DB
        return orderRepository.save(entity);
    }

    public OrderEntity toEntityOrder(Order order) {
        //convert OrderModel to OrderEntity
        OrderEntity entity = new OrderEntity();
        //searching user in DB by id
        entity.setUser(userRepository.findById(order.getUser().getId()).get());
        entity.setIncoming(order.getIncoming());
        for (OrderLine line : order.getOrderList()) {
            StorageEntity storage = new StorageEntity();
            storage.setOrder(entity);
            storage.setPhone(line.getPhone());
            storage.setCount(line.getCount());
            entity.getOrderList().add(storage);
        }
        entity.setPaid(false);
        return entity;
    }

    public List<PriceEntity> getPriceList(Order order) {
        //get prices from order
        List<PriceEntity> priceList = new ArrayList<>();
        for (OrderLine line : order.getOrderList()) {
            PriceEntity price = new PriceEntity();
            price.setPhone(line.getPhone());
            price.setPrice(line.getPrice());
            priceList.add(price);
        }
        return priceList;
    }


    public List<StorageLine> getStorageStock() {
        //convert request result
        List<StorageLine> storageLines = new ArrayList<>();
        List<Object[]> list = storageRepository.getGoodsStock();
        for (Object[] a : list) {
            StorageLine line = new StorageLine();
            BigDecimal count = (BigDecimal) a[0];
            BigInteger id = (BigInteger) a[1];
            line.setCount(count.longValue());
            line.setPhone(phoneRepository.findById(id.longValue()).get());
            line.setPrice(getLastPrice(line.getPhone()));
            storageLines.add(line);
        }
        return storageLines;
    }

    public List<PriceEntity> setPrices(List<PriceEntity> priceList) {
        //saving price list to DB
        return (List<PriceEntity>) priceRepository.saveAll(priceList);
    }

    public Double getLastPrice(PhoneEntity phone) {
        //get actual price
        List<PriceEntity> list = priceRepository.findByPhone_Id(phone.getId());
        return list.get(list.size() - 1).getPrice();
    }
}
