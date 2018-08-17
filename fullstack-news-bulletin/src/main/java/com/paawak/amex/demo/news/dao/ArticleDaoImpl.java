package com.paawak.amex.demo.news.dao;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public int insertArticle(Stream<Article> articles) {
        String sql = "INSERT INTO ARTICLE (ID, HEADLINE, URL, PUBLISHER_NAME, CLASSIFIER, PUBLISHER_URL, PUBLISHED_TIME) "
                + " VALUES (:id, :headline, :url, :publisherName, :classifier, :publisherUrl, :publishedTime)";
        SqlParameterSource[] sqlParameterSource =
                articles.map(article -> new BeanPropertySqlParameterSource(article)).collect(Collectors.toList()).toArray(new SqlParameterSource[0]);
        int[] result = jdbcOperations.batchUpdate(sql, sqlParameterSource);
        return Arrays.stream(result).sum();
    }

}
