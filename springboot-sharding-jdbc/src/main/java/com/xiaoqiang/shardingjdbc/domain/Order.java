package com.xiaoqiang.shardingjdbc.domain;

import lombok.Data;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_order")
@Data
public class Order {

    @Id
    private Long orderId;

    private Long userId;


    private  String period;

}
