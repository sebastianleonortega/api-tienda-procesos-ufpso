package com.api.ufpso.tienda.repository;

import com.api.ufpso.tienda.model.Article;
import com.api.ufpso.tienda.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("SELECT a FROM Article a WHERE a.category = :category")
    List<Article> findAllArticlesByCategory(@Param("category") Category category);
}
