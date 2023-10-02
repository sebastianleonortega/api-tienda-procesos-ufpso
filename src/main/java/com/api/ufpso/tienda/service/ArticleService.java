package com.api.ufpso.tienda.service;

import com.api.ufpso.tienda.model.Article;
import com.api.ufpso.tienda.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> findAllArticle(){
        return (List<Article>) articleRepository.findAll();
    }

    public Article createArticle(Article articulo){
        return articleRepository.save(articulo);
    }

    public Article getArticleById(Long id){
        return articleRepository.findById(id).get();
    }

    public Article updateArticle(Article articulo, Long id){
        Optional<Article> articleExist = articleRepository.findById(id);
        if(articleExist.isEmpty()){
            return null;
        }
        articleExist.get().setName(articulo.getName());
        articleExist.get().setDescription(articulo.getDescription());
        articleExist.get().setPrice(articulo.getPrice());
        articleExist.get().setStock(articulo.getStock());
        return articleRepository.save(articleExist.get());
    }

    public Boolean delete(Long id){
        Optional<Article> articleExist = articleRepository.findById(id);
        if(articleExist.isEmpty()){
            return false;
        }
        articleRepository.delete(articleExist.get());
        return true;
    }
}
