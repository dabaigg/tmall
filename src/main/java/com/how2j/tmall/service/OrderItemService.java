package com.how2j.tmall.service;

import com.how2j.tmall.pojo.Order;
import com.how2j.tmall.pojo.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderItemService {

    void add(OrderItem c);

    void delete(int id);
    void update(OrderItem c);
    OrderItem get(int id);
    List list();

    void fill(List<Order> os);

    void fill(Order o);

    int getSaleCount(int id);

    List<OrderItem> listByUser(int uid);
}