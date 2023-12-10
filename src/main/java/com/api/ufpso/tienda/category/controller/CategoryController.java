package com.api.ufpso.tienda.category.controller;

import com.api.ufpso.tienda.article.model.Article;
import com.api.ufpso.tienda.category.model.Category;
import com.api.ufpso.tienda.category.service.CategoryService;
import com.api.ufpso.tienda.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/all")
    public ResponseEntity<?> finAll(@RequestHeader(value = "Authorization") String token){
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Token no válido"));

        } else {
            return ResponseEntity.ok(categoryService.findAllCategory());
        }
        }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id, @RequestHeader(value = "Authorization") String token){
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Token no válido"));

        } else {
            return ResponseEntity.ok(categoryService.getCategoryById(id));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Category category, @RequestHeader(value = "Authorization") String token){
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Token no válido"));

        } else {
            return new ResponseEntity<>(categoryService.CreateCategory(category), HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Category category, @PathVariable Long id, @RequestHeader(value = "Authorization") String token){
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Token no válido"));

        } else {
            return new ResponseEntity(categoryService.updateCategory(category, id), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestHeader(value = "Authorization") String token){
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Token no válido"));

        } else {
            return new ResponseEntity(categoryService.delete(id), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<?> getAllArticlesByCategory(@PathVariable Long id, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Token no válido"));

        } else {
            List<Article> articles = categoryService.getAllArticleByCategory(id);
            if (articles != null) {
                return ResponseEntity.ok(articles);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
}
