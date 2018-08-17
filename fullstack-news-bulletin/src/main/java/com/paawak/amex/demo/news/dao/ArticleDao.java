package com.paawak.amex.demo.news.dao;

import java.util.stream.Stream;

import com.paawak.amex.demo.news.model.Article;

public interface ArticleDao {

    int insertArticle(Stream<Article> articles);

}
