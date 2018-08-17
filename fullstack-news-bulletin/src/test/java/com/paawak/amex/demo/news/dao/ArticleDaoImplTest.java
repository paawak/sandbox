package com.paawak.amex.demo.news.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

public class ArticleDaoImplTest {

    @Test
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void testSearchArticles() {
        // given
        String expectedSql =
                "SELECT ID, HEADLINE, URL, PUBLISHER_NAME, CLASSIFIER, PUBLISHER_URL, PUBLISHED_TIME FROM ARTICLE WHERE HEADLINE ILIKE :searchTerm";

        Map<String, Object> expectedParams = new HashMap<>();
        expectedParams.put("searchTerm", "%ABCDE%");

        NamedParameterJdbcOperations jdbcOperations = mock(NamedParameterJdbcOperations.class);
        ArgumentCaptor<String> sql = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Map> params = ArgumentCaptor.forClass(Map.class);
        ArgumentCaptor<BeanPropertyRowMapper> mapper = ArgumentCaptor.forClass(BeanPropertyRowMapper.class);

        ArticleDaoImpl testClass = new ArticleDaoImpl(jdbcOperations);

        // when
        testClass.searchArticles("ABCDE");

        // then
        verify(jdbcOperations).query(sql.capture(), params.capture(), mapper.capture());

        assertEquals(expectedSql, sql.getValue());
        assertEquals(expectedParams, params.getValue());

    }

}
