package com.store.controller;

import com.store.entity.Product;
import com.store.service.CategoryService;
import com.store.service.ProductService;
import com.store.util.Constant;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public String productList(Model model, @RequestParam(value = "page" , required = false, defaultValue = "1") int page,@RequestParam("cid")Optional<String> cid) throws Exception { // required ko bắt buộc default xet giá trị mặc định
        List<Product> products = new ArrayList<>();
        if(cid.isPresent()){
            try {
                Page<Product> productPage = productService.findCategoryProduct(cid.get(),Constant.PAGE_PRODUCT_CATEGORY_MAX_SIZE, page );
                products = productPage.getContent();
                model.addAttribute("totalPagesCategory", productPage.getTotalPages()); // tổng số trang
                model.addAttribute("currentPageCategory",page); // trang hiện tại
                model.addAttribute("categoryId",cid.get()); //
            } catch (Exception e) {
                products = productService.findAllProduct();
            }
        } else {
            try {
                Page<Product> productPage = productService.findAllProduct(Constant.PAGE_PRODUCT_MAX_SIZE, page );
                products = productPage.getContent();
                model.addAttribute("totalPages", productPage.getTotalPages()); // tổng số trang
                model.addAttribute("currentPage",page); // trang hiện tại
            } catch (Exception e) {
                products = productService.findAllProduct();
            }
        }
        model.addAttribute("item",products);
        return "/product/list";
    }

    @GetMapping("/list/search")
    public String productListSearch(Model model, @RequestParam(value = "page" , required = false, defaultValue = "1") int page,@RequestParam("search")Optional<String> search) throws Exception { // required ko bắt buộc default xet giá trị mặc định
        List<Product> products = new ArrayList<>();
        if(search.isPresent()){
            try {
                Page<Product> productPage = productService.findSearch(search.get(),Constant.PAGE_PRODUCT_CATEGORY_MAX_SIZE, page );
                products = productPage.getContent();
                model.addAttribute("totalPagesSearch", productPage.getTotalPages()); // tổng số trang
                model.addAttribute("currentPageCategory",page); // trang hiện tại
                model.addAttribute("categoryId",search.get()); //
            } catch (Exception e) {
                products = productService.findAllProduct();
            }
        } else {
            try {
                Page<Product> productPage = productService.findAllProduct(Constant.PAGE_PRODUCT_MAX_SIZE, page );
                products = productPage.getContent();
                model.addAttribute("totalPages", productPage.getTotalPages()); // tổng số trang
                model.addAttribute("currentPage",page); // trang hiện tại
            } catch (Exception e) {
                products = productService.findAllProduct();
            }
        }
        model.addAttribute("item",products);
        return "/product/list";
    }

    @GetMapping("/detail/{id}")
    public String productDetail(Model model, @PathVariable("id") Long id, @RequestParam(value = "page",required = false, defaultValue = "1") int page){
        List<Product> products = new ArrayList<>();
        Product product = productService.getOneProduct(id);
        try {
            Page<Product> productPage = productService.findCategoryProduct(product.getCategory().getId(),Constant.PAGE_PRODUCT_CATEGORY_MAX_SIZE, page );
            products = productPage.getContent();
            model.addAttribute("totalPages", productPage.getTotalPages()); // tổng số trang
            model.addAttribute("currentPage",page); // trang hiện tại
        }catch (Exception e) {
            products = productService.findAllProduct();
        }
        // sp
        model.addAttribute("item",product);
        model.addAttribute("items",products);
        return "/product/detail";
    }

}
