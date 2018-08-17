package com.paawak.amex.demo.news.service;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;

import com.paawak.amex.demo.news.model.Article;

public class NewsUploadServiceImplTest {

    @Test
    public void testParseArticle() {
        // given
        String rawText = "\"23\", \"This is a headline\", \"The url\",\"The publisherName\", \"The classifier\", \"The publisherUrl\",\"1394470370698\"";
        Article expected =
                new Article(23L, "This is a headline", "The url", "The publisherName", "The classifier", "The publisherUrl", new Date(1394470370698L));
        NewsUploadServiceImpl testClass = new NewsUploadServiceImpl(null);

        // when
        Article result = testClass.parseArticle(rawText);

        // then
        assertEquals(expected, result);
    }

    @Test
    public void testStripLeadingTrailingChar_1() {
        // given
        NewsUploadServiceImpl testClass = new NewsUploadServiceImpl(null);

        // when
        String result = testClass.stripLeadingTrailingChar("\"23\"");

        // then
        assertEquals("23", result);
    }

    @Test
    public void testStripLeadingTrailingChar_2() {
        // given
        NewsUploadServiceImpl testClass = new NewsUploadServiceImpl(null);

        // when
        String result = testClass.stripLeadingTrailingChar("   \"23\"  ");

        // then
        assertEquals("23", result);
    }

    @Test
    public void testStripLeadingTrailingChar_3() {
        // given
        NewsUploadServiceImpl testClass = new NewsUploadServiceImpl(null);

        // when
        String result = testClass.stripLeadingTrailingChar("   \"  2  3  \"  ");

        // then
        assertEquals("  2  3  ", result);
    }

}
