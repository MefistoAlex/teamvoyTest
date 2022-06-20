package com.teamvoy.task.service;

import com.teamvoy.task.entity.OrderEntity;
import com.teamvoy.task.entity.PhoneEntity;
import com.teamvoy.task.entity.PriceEntity;
import com.teamvoy.task.entity.UserEntity;
import com.teamvoy.task.model.Order;
import com.teamvoy.task.model.OrderLine;
import com.teamvoy.task.model.User;
import com.teamvoy.task.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ManagerServiceTest {
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
    private ManagerService managerService;

    @Test
    void addingPhoneTest(){
        //given
        PhoneEntity phone  = new PhoneEntity();
        phone.setModel("test");
        //when
        phoneRepository.save(phone);
        //then
        Assertions.assertThat(phone.getId()).isGreaterThan(0);
    }

    @Test
    void getLastPriceReturnLastPrice(){
        //given
        PhoneEntity phone  = new PhoneEntity();
        phone.setModel("test");
        phoneRepository.save(phone);
        PriceEntity price = new PriceEntity();
        price.setPhone(phone);
        price.setPrice(3D);
        priceRepository.save(price);
        //when
        Double lastPrice = managerService.getLastPrice(phone);
        //then
        Assertions.assertThat(lastPrice).isEqualTo(3D);
    }
    @Test
    void addingPriceList(){
        //given
        List<PriceEntity> priceList  = new ArrayList<>();
        PhoneEntity phone  = new PhoneEntity();
        phone.setModel("test");
        phoneRepository.save(phone);
        PriceEntity price = new PriceEntity();
        price.setPhone(phone);
        price.setPrice(23D);
        priceList.add(price);
        //when
        List<PriceEntity> priceEntityList = managerService.setPrices(priceList);
        //then
        Assertions.assertThat(priceEntityList).isNotEmpty();
    }
    @Test
    void getPriseListFromOrder(){
        //given
        Order order = new Order();
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
        List<PriceEntity> priceList = managerService.getPriceList(order);
        //then
        Assertions.assertThat(priceList).size().isEqualTo(orderList.size());
    }
    @Test
    void addingIncomingOrderTest(){
        //given
        Order order = new Order();
        order.setIncoming(true);
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
        OrderEntity  entity = managerService.incomingOrder(order);
        //then
        Assertions.assertThat(entity.getId()).isGreaterThan(0);
    }
}
