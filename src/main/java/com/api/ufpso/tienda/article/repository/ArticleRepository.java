package com.api.ufpso.tienda.article.repository;

import com.api.ufpso.tienda.article.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
