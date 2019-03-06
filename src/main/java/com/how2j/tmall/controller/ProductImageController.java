package com.how2j.tmall.controller;


import com.how2j.tmall.pojo.Product;
import com.how2j.tmall.pojo.ProductImage;
import com.how2j.tmall.service.ProductImageService;
import com.how2j.tmall.service.ProductService;
import com.how2j.tmall.util.ImageUtil;
import com.how2j.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductImageController {

    @Autowired
    ProductImageService productImageService;

    @Autowired
    ProductService productService;

    @RequestMapping("admin_productImage_list")
    public String list(int pid, Model model){
        Product p = productService.get(pid);
        List<ProductImage> single = productImageService.list(pid,ProductImageService.type_single);
        List<ProductImage> detail = productImageService.list(pid,ProductImageService.type_deatil);
        model.addAttribute("p",p);
        model.addAttribute("pisSingle",single);
        model.addAttribute("pisDetail",detail);
        return "admin/listProductImage";
    }

    @RequestMapping("admin_productImage_add")
    public String add(ProductImage pi, HttpSession httpSession, UploadedImageFile uploadedImageFile){
        productImageService.add(pi);
        String fileName = pi.getId()+".jpg";
        String imageFolder;
        String imageFolder_small = null;
        String imageFolder_middle = null;
        if (ProductImageService.type_single.equals(pi.getType())){
            imageFolder = httpSession.getServletContext().getRealPath("img/productSingle");
            imageFolder_small = httpSession.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle = httpSession.getServletContext().getRealPath("img/productSingle_middle");
        }else{
            imageFolder = httpSession.getServletContext().getRealPath("img/productDetail");
        }
        File file = new File(imageFolder,fileName);
        file.getParentFile().mkdir();
        try {
            uploadedImageFile.getImage().transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img,"jpg",file);
            if (ProductImageService.type_single.equals(pi.getType())){
                File file_small = new File(imageFolder_small,fileName);
                File file_middle = new File(imageFolder_middle,fileName);
                ImageUtil.resizeImage(file,56,56,file_small);
                ImageUtil.resizeImage(file,217,190,file_middle);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:admin_productImage_list?pid="+pi.getPid();
    }

    @RequestMapping("admin_productImage_delete")
    public String delete(int id, HttpSession session){
        ProductImage pi = productImageService.get(id);
        String fileName = pi.getId()+".jpg";
        String imageFolder;
        String imageFolder_small = null;
        String imageFolder_middle = null;
        if (ProductImageService.type_single.equals(pi.getType())){
            imageFolder = session.getServletContext().getRealPath("img/productSingle");
            imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle = session.getServletContext().getRealPath("img.product_middle");
            File  imageFile = new File(imageFolder,fileName);
            File f_small = new File(imageFolder_small,fileName);
            File f_middle = new File(imageFolder_middle,fileName);
            imageFile.delete();
            f_small.delete();
            f_middle.delete();
        }else{
            imageFolder = session.getServletContext().getRealPath("img/productDetail");
            File f = new File(imageFolder,fileName);
            f.delete();
        }

        productImageService.delete(id);
        return "redirect:admin_productImage_list?pid="+pi.getPid();
    }


}
