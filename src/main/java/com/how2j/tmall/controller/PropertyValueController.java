package com.how2j.tmall.controller;

import com.how2j.tmall.pojo.Product;
import com.how2j.tmall.pojo.PropertyValue;
import com.how2j.tmall.service.ProductService;
import com.how2j.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyValueController {

    @Autowired
    ProductService productService;
    @Autowired
    PropertyValueService propertyValueService;

    @RequestMapping("admin_propertyValue_edit")
    public String edit(int pid, Model model){
        Product p = productService.get(pid);
        propertyValueService.init(p);
        List<PropertyValue> list = propertyValueService.list(p.getId());
        model.addAttribute("p",p);
        model.addAttribute("pvs",list);
        return "admin/editPropertyValue";
    }

    @RequestMapping("admin_propertyValue_update")
    @ResponseBody
    public String update(PropertyValue pv) {
        propertyValueService.update(pv);
        return "success";
    }
}
