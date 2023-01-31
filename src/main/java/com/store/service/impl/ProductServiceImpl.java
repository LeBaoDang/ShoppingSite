package com.store.service.impl;

import com.store.entity.Product;
import com.store.repository.ProductRepo;
import com.store.service.ProductService;
import com.store.service.impl.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Override
    public List<Product> findAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public Product GetOneProduct(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(!optionalProduct.isPresent()){
            throw new ResourceNotFoundException(String.format("product.not.found.with.id:%s", id));
        }
        return productRepo.findById(id).get();
    }

    @Override
    public Page<Product> findAllProduct(int pageSize, int pageNumber) throws Exception {
        if(pageNumber >= 1) {
            return productRepo.findAll(PageRequest.of(pageNumber - 1,pageSize));
        } else {
            throw new Exception("page number must be greater than 0");
        }
    }

    @Override
    public Page<Product> findCategoryProduct(String categoryId, int pageSize, int pageNumber) throws Exception {
        if(pageNumber >= 1) {
            return productRepo.findCategoryProduct(categoryId,PageRequest.of(pageNumber - 1,pageSize));
        } else {
            throw new Exception("page number must be greater than 0");  
        }
    }



}
