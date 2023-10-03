package com.api.ufpso.tienda.repository;

import com.api.ufpso.tienda.model.Article;
import com.api.ufpso.tienda.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Article> getCategoriesByArticle (Long id);
}
