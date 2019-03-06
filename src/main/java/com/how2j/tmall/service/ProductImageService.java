package com.how2j.tmall.service;

import com.how2j.tmall.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {
    String type_single = "type_single";
    String type_deatil = "type_detail";

    void add(ProductImage productImage);
    void delete(int id);
    void update(ProductImage productImage);
    ProductImage get(int id);
    List list(int pid,String type);
}
