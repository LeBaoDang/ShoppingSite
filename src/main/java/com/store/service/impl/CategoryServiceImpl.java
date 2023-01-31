package com.store.service.impl;

import com.store.entity.Category;
import com.store.repository.CategoryRepo;
import com.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

}
