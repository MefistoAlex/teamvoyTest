package com.teamvoy.task.service;

import com.teamvoy.task.entity.OrderEntity;
import com.teamvoy.task.entity.StorageEntity;
import com.teamvoy.task.entity.UserEntity;
import com.teamvoy.task.exception.OutOfStockException;
import com.teamvoy.task.exception.UserAlreadyExistException;
import com.teamvoy.task.model.Order;
import com.teamvoy.task.model.OrderLine;
import com.teamvoy.task.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class UserService {
    //repositories
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StorageRepository storageRepository;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        //user registration
        String username = user.getUsername();
        UserEntity userEntity = userRepository.findByUsername(username);
        //checking for dublicate username
        if (userEntity != null) {
            throw new UserAlreadyExistException("User already exist");
        }
        //save user to DB
        return userRepository.save(user);
    }

    public void outgoingOrder(Order order) throws OutOfStockException {
        // user order to purchase goods
        order.setIncoming(false);
        order.calculateTotalCost();
        //checking for availability goods
        inStock(order);
        //turning model to entity
        OrderEntity entity = toEntityOrder(order);
        //creating outgoing order thread
        outgoingOrderThread outgoingOrderThread = new outgoingOrderThread(orderRepository, entity);
        outgoingOrderThread.start();
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
        entity.setPaid(false);
        return entity;
    }

    public void inStock(Order order) throws OutOfStockException {
        //checking for availability goods
        for (OrderLine line : order.getOrderList()) {
            BigDecimal stock = storageRepository.getStockByPhone(line.getPhone().getId());
            if (stock == null || (stock.longValue() - line.getCount()) < 0) {
                throw new OutOfStockException(line.getPhone().getModel() + " is out of stock");
            }
        }
    }

    public void payOrder(Long order_id) {
        //paying order by user
        OrderEntity order = orderRepository.findById(order_id).get();
        order.setPaid(true);
        orderRepository.save(order);
    }
}
