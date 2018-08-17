package com.paawak.amex.demo.news.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.paawak.amex.demo.news.model.Article;

@Repository
public class ArticleDaoImpl implements ArticleDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    public ArticleDaoImpl(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public int insertArticles(List<Article> articles) {
        String sql = "INSERT INTO ARTICLE (ID, HEADLINE, URL, PUBLISHER_NAME, CLASSIFIER, PUBLISHER_URL, PUBLISHED_TIME) "
                + " VALUES (:id, :headline, :url, :publisherName, :classifier, :publisherUrl, :publishedTime)";
        SqlParameterSource[] sqlParameterSource =
                articles.stream().map(article -> new BeanPropertySqlParameterSource(article)).collect(Collectors.toList()).toArray(new SqlParameterSource[0]);
        int[] result = jdbcOperations.batchUpdate(sql, sqlParameterSource);
        return Arrays.stream(result).sum();
    }

    @Override
    public List<Article> getArticles() {
        // To implement paging: SELECT * FROM ARTICLE LIMIT 10 OFFSET 3
        String sql = "SELECT ID, HEADLINE, URL, PUBLISHER_NAME, CLASSIFIER, PUBLISHER_URL, PUBLISHED_TIME FROM ARTICLE";
        return jdbcOperations.query(sql, new BeanPropertyRowMapper<Article>(Article.class));
    }

    @Override
    public List<Article> searchArticles(String searchTerm) {
        String sql = "SELECT ID, HEADLINE, URL, PUBLISHER_NAME, CLASSIFIER, PUBLISHER_URL, PUBLISHED_TIME FROM ARTICLE WHERE HEADLINE ILIKE :searchTerm";
        Map<String, Object> params = new HashMap<>();
        params.put("searchTerm", "%" + searchTerm + "%");
        return jdbcOperations.query(sql, params, new BeanPropertyRowMapper<Article>(Article.class));
    }

}
