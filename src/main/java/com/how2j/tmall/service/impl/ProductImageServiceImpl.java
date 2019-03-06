package com.how2j.tmall.service.impl;

import com.how2j.tmall.mapper.ProductImageMapper;
import com.how2j.tmall.mapper.ProductMapper;
import com.how2j.tmall.pojo.ProductImage;
import com.how2j.tmall.pojo.ProductImageExample;
import com.how2j.tmall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    ProductImageMapper productImageMapper;
    @Override
    public void add(ProductImage productImage) {
        productImageMapper.insert(productImage);
    }

    @Override
    public void delete(int id) {
        productImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ProductImage productImage) {
        productImageMapper.updateByPrimaryKeySelective(productImage);
    }

    @Override
    public ProductImage get(int id) {
        return productImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int pid, String type) {
        ProductImageExample productImageExample = new ProductImageExample();
        productImageExample.createCriteria()
                .andPidEqualTo(pid)
                .andTypeEqualTo(type);
        productImageExample.setOrderByClause("id desc");
        return productImageMapper.selectByExample(productImageExample);
    }
}
