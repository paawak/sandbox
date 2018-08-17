package com.paawak.amex.demo.news.service;

import java.io.InputStream;
import java.util.List;

import com.paawak.amex.demo.news.model.Article;

public interface NewsService {

    int uploadRawNews(InputStream is);

    List<Article> getArticles();

    List<Article> searchArticles(String searchTerm);

}
