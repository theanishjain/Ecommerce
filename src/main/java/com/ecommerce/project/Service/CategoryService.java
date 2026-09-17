package com.ecommerce.project.Service;

import com.ecommerce.project.exception.APIException;
import com.ecommerce.project.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories() throws APIException;
    void AddCategory(Category category) throws APIException;
    String deleteCategory(Long categoryId);
    Category updateCategory(Category category, Long categoryId);
}
