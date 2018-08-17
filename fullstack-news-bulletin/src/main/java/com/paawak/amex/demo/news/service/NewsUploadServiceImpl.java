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
public class NewsUploadServiceImpl implements NewsUploadService {

    private final ArticleDao articleDao;

    public NewsUploadServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public int uploadRawNews(InputStream is) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()))) {

            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                lines.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    Article parseArticle(String text) {
        String[] tokens = text.split(",");
        return new Article(Long.parseLong(stripLeadingTrailingChar(tokens[0])), stripLeadingTrailingChar(tokens[1]), stripLeadingTrailingChar(tokens[2]),
                stripLeadingTrailingChar(tokens[3]), stripLeadingTrailingChar(tokens[4]), stripLeadingTrailingChar(tokens[5]),
                new Date(Long.parseLong(stripLeadingTrailingChar(tokens[6]))));
    }

    String stripLeadingTrailingChar(String text) {
        text = text.trim();
        if (text.length() <= 2) {
            return text;
        }
        return text.substring(1, text.length() - 1);
    }

}
