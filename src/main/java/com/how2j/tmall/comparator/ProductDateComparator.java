package com.how2j.tmall.comparator;

import com.how2j.tmall.pojo.Product;

import java.util.Comparator;

//新品比较器 把 创建日期晚的放前面
public class ProductDateComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getCreateDate().compareTo(o1.getCreateDate());
    }
}
