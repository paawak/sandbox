package com.paawak.amex.demo.news.service;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;

import com.paawak.amex.demo.news.model.Article;

public class NewsServiceImplTest {

    @Test
    public void testParseArticle() {
        // given
        String rawText = "\"23\",\"This is a headline\",\"The url\",\"The publisherName\",\"The classifier\",\"The publisherUrl\",\"1394470370698\"";
        Article expected =
                new Article(23L, "This is a headline", "The url", "The publisherName", "The classifier", "The publisherUrl", new Date(1394470370698L));
        NewsServiceImpl testClass = new NewsServiceImpl(null);

        // when
        Article result = testClass.parseArticle(rawText);

        // then
        assertEquals(expected, result);
    }

}
