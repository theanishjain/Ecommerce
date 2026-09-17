package com.ecommerce.project.Service;

import com.ecommerce.project.exception.APIException;
import com.ecommerce.project.exception.ResourseNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getCategories() throws APIException {
        if(categoryRepository.count()==0){
            throw new APIException("No Categories Exist");
        }
        return categoryRepository.findAll();
    }

    @Override
    public void AddCategory(Category category) throws APIException {
        Category savingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savingCategory!=null){
            throw new APIException("Category with Category Name: " + category.getCategoryName()+ " Already exists!!!");
        }
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category setCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourseNotFoundException("Category", "Id", categoryId));

        categoryRepository.delete(setCategory);
        return "Category with CategoryId: " + categoryId + " removed successfully";
    }

    public Category updateCategory(Category category, Long categoryId) {

        Category setCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourseNotFoundException("Category", "Id", categoryId));

        setCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(setCategory);
    }
}
