package com.api.ufpso.tienda.repository;

import com.api.ufpso.tienda.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
