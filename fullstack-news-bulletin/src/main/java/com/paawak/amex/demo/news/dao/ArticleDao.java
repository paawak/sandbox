package com.paawak.amex.demo.news.dao;

import java.util.List;

import com.paawak.amex.demo.news.model.Article;

public interface ArticleDao {

    int insertArticles(List<Article> articles);

    List<Article> getArticles();

    List<Article> searchArticles(String searchTerm);

}
