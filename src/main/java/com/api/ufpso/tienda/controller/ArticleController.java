package com.api.ufpso.tienda.controller;

import com.api.ufpso.tienda.model.Article;
import com.api.ufpso.tienda.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("article")
    public ResponseEntity<List<Article>> findAll(){
        return ResponseEntity.ok(articleService.findAllArticle());
    }

    @GetMapping("article/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id){
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PostMapping("article")
    public ResponseEntity<Article> create(@RequestBody Article article){
        return new ResponseEntity<>(articleService.createArticle(article), HttpStatus.CREATED);
    }

    @PutMapping("article/{id}")
    public ResponseEntity<Article> update(@RequestBody Article article, @PathVariable Long id){
        return new ResponseEntity<>(articleService.updateArticle(article, id), HttpStatus.OK);
    }

    @DeleteMapping("article/{id}")
    public  ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity(articleService.delete(id), HttpStatus.NO_CONTENT);
    }

}
