package com.how2j.tmall.service.impl;

import com.how2j.tmall.mapper.ReviewMapper;
import com.how2j.tmall.pojo.Review;
import com.how2j.tmall.pojo.ReviewExample;
import com.how2j.tmall.pojo.User;
import com.how2j.tmall.service.ReviewService;
import com.how2j.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    UserService userService;
    @Override
    public void add(Review c) {
        reviewMapper.insert(c);
    }

    @Override
    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void update(Review c) {
        reviewMapper.updateByPrimaryKey(c);
    }

    @Override
    public Review get(int id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int pid) {
        ReviewExample example = new ReviewExample();
        example.createCriteria().andPidEqualTo(pid);
        example.setOrderByClause("id desc");
        List<Review> rs = reviewMapper.selectByExample(example);
        setUser(rs);
        return rs;
    }

    private void setUser(List<Review> reviews){
        for (Review r :
                reviews) {
            setUser(r);
        }
    }

    private void setUser(Review review){
        int uid = review.getUid();
        User u = userService.get(uid);
        review.setUser(u);
    }

    @Override
    public int getCount(int pid) {
        return list(pid).size();
    }
}
