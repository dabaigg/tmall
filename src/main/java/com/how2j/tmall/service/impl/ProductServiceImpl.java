package com.how2j.tmall.service.impl;

import com.how2j.tmall.mapper.ProductMapper;
import com.how2j.tmall.pojo.Category;
import com.how2j.tmall.pojo.Product;
import com.how2j.tmall.pojo.ProductExample;
import com.how2j.tmall.pojo.ProductImage;
import com.how2j.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductImageService productImageService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    ReviewService reviewService;

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product products) {
        productMapper.updateByPrimaryKeySelective(products);
    }

    @Override
    public Product get(int id) {
        Product p = productMapper.selectByPrimaryKey(id);
        Category c = categoryService.get(p.getCid());
        setFirstProductImage(p);
        p.setCategory(c);
        return p;
    }

    @Override
    public List list(int cid) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andCidEqualTo(cid);
        productExample.setOrderByClause("id desc");
        List<Product> result = productMapper.selectByExample(productExample);
        for (Product p: result) {
            Category c = categoryService.get(p.getCid());
            p.setCategory(c);
            setFirstProductImage(p);
        }
        return result;
    }

    @Override
    public void setFirstProductImage(Product p) {
        List<ProductImage> list = productImageService.list(p.getId(), ProductImageService.type_single);
        if(!list.isEmpty()){
            ProductImage pi = list.get(0);
            p.setFirstProductImage(pi);
        }
    }

    public void setCategory(List<Product> ps){
        for (Product p : ps)
            setCategory(p);
    }
    public void setCategory(Product p){
        int cid = p.getCid();
        Category c = categoryService.get(cid);
        p.setCategory(c);
    }
    @Override
    public List<Product> search(String keyword) {
        ProductExample example = new ProductExample();
        example.createCriteria().andNameLike("%" + keyword + "%");
        example.setOrderByClause("id desc");
        List result = productMapper.selectByExample(example);
        setFirstProductImage(result);
        setCategory(result);
        return result;

    }

    public void setFirstProductImage(List<Product> ps) {
        for (Product p :
                ps) {
            setFirstProductImage(p);
        }
    }

    @Override
    public void fill(List<Category> cs) {
        for (Category c : cs) {
            fill(c);
        }
    }

    @Override
    public void fill(Category c) {
        List<Product> ps = list(c.getId());
        c.setProducts(ps);
    }

    @Override
    public void fillByRow(List<Category> cs) {
        int productNumberEachRow = 8;
        for (Category c : cs) {
            List<Product> products =  c.getProducts();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            c.setProductsByRow(productsByRow);
        }
    }

    @Override
    public void setSaleAndReviewNumber(Product product) {
        int saleCount = orderItemService.getSaleCount(product.getId());
        product.setSaleCount(saleCount);
        int count = reviewService.getCount(product.getId());
        product.setReviewCount(count);
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> list) {
        for (Product p :
                list) {
            setSaleAndReviewNumber(p);
        }
    }
}




















