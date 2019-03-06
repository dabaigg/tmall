package com.how2j.tmall.service;

import com.how2j.tmall.pojo.Product;
import com.how2j.tmall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {
    void init(Product product);
    void update(PropertyValue propertyValue);

    PropertyValue get(int ptid,int pid);
    List<PropertyValue> list(int pid);
}
