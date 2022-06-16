package com.teamvoy.task.service;

import com.teamvoy.task.entity.OrderEntity;
import com.teamvoy.task.entity.StorageEntity;
import com.teamvoy.task.entity.UserEntity;
import com.teamvoy.task.exception.UserAlreadyExistException;
import com.teamvoy.task.model.Order;
import com.teamvoy.task.model.OrderLine;
import com.teamvoy.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        String  str = user.getUsername();
        UserEntity userEntity = userRepository.findByUsername(str);
        if (userEntity!=null){
            throw new UserAlreadyExistException("User already exist");
        }
        return userRepository.save(user);
    }

    public void outgoingOrder(Order order) {
        order.setIncoming(false);
        OrderEntity entity = toEntityOrder(order);
    }

    private OrderEntity toEntityOrder(Order order) {
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
}
