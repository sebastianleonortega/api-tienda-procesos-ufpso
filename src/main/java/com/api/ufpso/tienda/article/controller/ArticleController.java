package com.api.ufpso.tienda.article.controller;

import com.api.ufpso.tienda.article.model.Article;
import com.api.ufpso.tienda.article.service.ArticleService;
import com.api.ufpso.tienda.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/all")
    public ResponseEntity<List<Article>> findAll(){
        return ResponseEntity.ok(articleService.findAllArticle());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id){
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Article article, @RequestHeader(value = "Authorization")String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        } else {
            return new ResponseEntity<>(articleService.createArticle(article), HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> update(@RequestBody Article article, @PathVariable Long id){
        return new ResponseEntity<>(articleService.updateArticle(article, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity(articleService.delete(id), HttpStatus.NO_CONTENT);
    }

}
