package com.xiaoqiang.shardingjdbc.repository;

import com.xiaoqiang.shardingjdbc.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Long> {
}
