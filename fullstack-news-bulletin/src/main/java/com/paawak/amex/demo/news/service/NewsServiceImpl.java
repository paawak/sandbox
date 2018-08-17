package com.paawak.amex.demo.news.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.paawak.amex.demo.news.dao.ArticleDao;
import com.paawak.amex.demo.news.model.Article;

@Service
public class NewsServiceImpl implements NewsService {

    private final ArticleDao articleDao;

    public NewsServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public int uploadRawNews(InputStream is) {

        List<Article> articles = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()))) {

            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                articles.add(parseArticle(line));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (articles.isEmpty()) {
            throw new RuntimeException("No records found!");
        }

        return articleDao.insertArticles(articles);
    }

    @Override
    public List<Article> getArticles() {
        return articleDao.getArticles();
    }

    Article parseArticle(String text) {
        String[] tokens = text.split("\",\"");

        return new Article(Long.parseLong(tokens[0].substring(1)), tokens[1], tokens[2], tokens[3], tokens[4], tokens[5],
                new Date(Long.parseLong(tokens[6].substring(0, tokens[6].length() - 1))));
    }

}
