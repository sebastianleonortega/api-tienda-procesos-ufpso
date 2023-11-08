package com.api.ufpso.tienda.category.repository;

import com.api.ufpso.tienda.article.model.Article;
import com.api.ufpso.tienda.category.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("SELECT a FROM Article a WHERE a.category = :category")
    List<Article> findAllArticlesByCategory(@Param("category") Category category);
}
