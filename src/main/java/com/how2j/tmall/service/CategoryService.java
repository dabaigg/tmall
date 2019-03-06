package com.how2j.tmall.service;

import com.how2j.tmall.pojo.Category;
import com.how2j.tmall.util.Page;

import java.util.List;

public interface CategoryService {
    List<Category> list();
    void add(Category category);
    void delete(int id);
    Category get(int id);
    void update(Category category);


}
