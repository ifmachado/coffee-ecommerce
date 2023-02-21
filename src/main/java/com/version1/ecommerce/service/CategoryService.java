package com.version1.ecommerce.service;

import com.version1.ecommerce.model.Category;
import com.version1.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryrepository;

    public void createCategory(Category category) {
        categoryrepository.save(category);
    }

    public List<Category> listCategory() {
        return categoryrepository.findAll();
    }

}
