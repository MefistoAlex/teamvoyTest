package com.teamvoy.task.service;

import com.teamvoy.task.entity.PhoneEntity;
import com.teamvoy.task.entity.UserEntity;
import com.teamvoy.task.exception.OutOfStockException;
import com.teamvoy.task.exception.UserAlreadyExistException;
import com.teamvoy.task.model.Order;
import com.teamvoy.task.model.OrderLine;
import com.teamvoy.task.model.User;
import com.teamvoy.task.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserServiceTest {
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
    @Autowired
    private UserService userService;

    @Test
    void ifTwoSameUsernameRegistrationException(){
        //given
        UserEntity user1 = new UserEntity();
        user1.setUsername("test");
        UserEntity user2 = new UserEntity();
        user2.setUsername("test");
        //when
        try{
            userService.registration(user1);
            userService.registration(user2);
        }catch (UserAlreadyExistException e){
            Assertions.assertTrue(true);
        }
    }
    @Test
    void inStockException(){
        //given
        Order order = new Order();
        order.setIncoming(false);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("test");
        userEntity.setPassword("pass");
        userEntity.setManager(true);
        userRepository.save(userEntity);
        order.setUser(User.toModel(userEntity));
        PhoneEntity phone  = new PhoneEntity();
        phone.setModel("test");
        phoneRepository.save(phone);
        List<OrderLine> orderList = new ArrayList<>();
        OrderLine orderLine = new OrderLine();
        orderLine.setPhone(phone);
        orderLine.setCount(1L);
        orderLine.setPrice(23.4D);
        orderList.add(orderLine);
        order.setOrderList(orderList);
        //when
        Executable executable = ()->userService.inStock(order);
        Assertions.assertThrows(OutOfStockException.class, executable);
    }

}
