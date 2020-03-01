package com.xiaoqiang.shardingjdbc.controller;

import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import com.xiaoqiang.shardingjdbc.domain.Order;
import com.xiaoqiang.shardingjdbc.mapper.OrderMapper;
import com.xiaoqiang.shardingjdbc.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private KeyGenerator keyGenerator;

    @RequestMapping("/create")
    public Object createOrder(){
        String[] per = new String[]{"202001","202002"};
        for (int i = 20; i < 30; i++) {
            Order order = new Order();
            order.setUserId((long) i);
            order.setOrderId((long) i);
            order.setPeriod(per[(int)Math.floor(Math.random()*2)]);
            orderRepository.save(order);
        }
        for (int i = 30; i < 40; i++) {
            Order order = new Order();
            order.setUserId((long) i + 1);
            order.setOrderId((long) i);
            order.setPeriod(per[(int)Math.floor(Math.random()*2)]);
            orderRepository.save(order);
        }

//        for (int i = 0; i < 20; i++) {
//            Order order = new Order();
//            order.setOrderId(keyGenerator.generateKey().longValue());
//            order.setUserId(keyGenerator.generateKey().longValue());
//            orderRepository.save(order);
//        }
        return "success";
    }

    @RequestMapping("/insert")
    public Object insertOrder(){
        for (int i = 20; i < 30; i++) {
            Order order = new Order();
            order.setUserId((long) i);
            order.setOrderId((long) i);
            orderMapper.insert(order);
        }
        return "success";
    }

    @RequestMapping("queryById/{orderId}")
    public Object queryById(@PathVariable("orderId") String orderId){
        List<String> strList = Arrays.asList(orderId.split(","));
        List<Long> orderIdList = strList.stream().map(item->Long.parseLong(item)).collect(Collectors.toList());
        return orderMapper.queryById(orderIdList);
    }
    @RequestMapping("queryAll")
    public Object queryAll(){
        return orderRepository.findAll();
    }



}
