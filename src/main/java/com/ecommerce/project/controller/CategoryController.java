package com.ecommerce.project.controller;

import com.ecommerce.project.Service.CategoryService;
import com.ecommerce.project.exception.APIException;
import com.ecommerce.project.model.Category;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/public/categories")
    public List<Category> getCategories() throws APIException {
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK).getBody();
    }

    @PostMapping("/api/admin/categories")
    public ResponseEntity<String> AddCategory(@Valid @RequestBody Category category) throws APIException {
        categoryService.AddCategory(category);
        return new ResponseEntity<>("Categories Added Successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);

    }

    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category, @PathVariable("categoryId") Long categoryId){
            Category Updatedcategory = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>("Category with CategoryId: " + categoryId + " Updated", HttpStatus.OK);
    }
}
