package com.api.ufpso.tienda.category.controller;

import com.api.ufpso.tienda.article.model.Article;
import com.api.ufpso.tienda.category.model.Category;
import com.api.ufpso.tienda.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("category")
    public ResponseEntity<List<Category>> finAll(){
        return ResponseEntity.ok(categoryService.findAllCategory());
    }

    @GetMapping("category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping("category")
    public ResponseEntity<Category> create(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.CreateCategory(category), HttpStatus.CREATED);
    }

    @PutMapping("category/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable Long id){
        return new ResponseEntity(categoryService.updateCategory(category, id), HttpStatus.OK);
    }

    @DeleteMapping("category/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity(categoryService.delete(id), HttpStatus.NO_CONTENT);
    }

    @GetMapping("category/article/{id}")
    public ResponseEntity<List<Article>> getAllArticlesByCategory(@PathVariable Long id) {
        List<Article> articles = categoryService.getAllArticleByCategory(id);
        if (articles != null) {
            return ResponseEntity.ok(articles);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}