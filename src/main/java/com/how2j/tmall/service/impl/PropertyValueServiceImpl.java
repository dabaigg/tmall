package com.how2j.tmall.service.impl;

import com.how2j.tmall.mapper.PropertyValueMapper;
import com.how2j.tmall.pojo.Product;
import com.how2j.tmall.pojo.Property;
import com.how2j.tmall.pojo.PropertyValue;
import com.how2j.tmall.pojo.PropertyValueExample;
import com.how2j.tmall.service.PropertyService;
import com.how2j.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    PropertyService propertyService;

    @Override
    public void init(Product product) {
        List<Property> list = propertyService.list(product.getCid());
        for (Property p :
                list) {
            PropertyValue pv = get(p.getId(), product.getId());
            if (pv==null){
                pv = new PropertyValue();
                pv.setPid(product.getId());
                pv.setPtid(p.getId());
                propertyValueMapper.insert(pv);
            }
        }
    }

    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueMapper.updateByPrimaryKey(propertyValue);
    }

    @Override
    public PropertyValue get(int ptid, int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPtidEqualTo(ptid).andPidEqualTo(pid);
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        if (pvs.isEmpty()){
            return null;
        }
        return pvs.get(0);
    }

    @Override
    public List<PropertyValue> list(int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> result = propertyValueMapper.selectByExample(example);
        for (PropertyValue pv :
                result) {
            Property property = propertyService.get(pv.getPtid());
            pv.setProperty(property);
        }
        return result;
    }
}
