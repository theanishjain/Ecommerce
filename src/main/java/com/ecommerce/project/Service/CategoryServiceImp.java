package com.ecommerce.project.Service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    private List<Category> categories = new ArrayList<>();
    private long nextId = 1;

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public void AddCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));

        categories.remove(category);
        return "Category with CategoryId: " + categoryId + " removed successfully";
    }

    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> optionalcategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();

        if (optionalcategory.isPresent()) {
            Category existingcategory = optionalcategory.get();
            existingcategory.setCategoryName(category.getCategoryName());
            return existingcategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found");
        }
    }
}
