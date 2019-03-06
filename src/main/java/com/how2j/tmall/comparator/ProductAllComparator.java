package com.how2j.tmall.comparator;

import com.how2j.tmall.pojo.Product;

import java.util.Comparator;

//综合比较器 把 销量x评价 高的放前面
public class ProductAllComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return o2.getReviewCount()*o2.getSaleCount()-o1.getReviewCount()*o1.getSaleCount();
    }
}
