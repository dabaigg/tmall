package com.how2j.tmall.service;

import com.how2j.tmall.pojo.Category;
import com.how2j.tmall.pojo.Product;

import java.util.List;

public interface ProductService {
    void add(Product product);
    void delete(int id);
    void update(Product products);
    Product get(int id);
    List list(int cid);
    void setFirstProductImage(Product p);
    List<Product> search(String keyword);

    //为分类填充产品
    void fill(Category category);

    //多个分类填充产品
    void fill(List<Category> categories);

    //多个分类填充推荐产品集合
    void fillByRow(List<Category> categories);

    //为产品设置销量和评价数量
    void setSaleAndReviewNumber(Product product);
    void setSaleAndReviewNumber(List<Product> list);
}
