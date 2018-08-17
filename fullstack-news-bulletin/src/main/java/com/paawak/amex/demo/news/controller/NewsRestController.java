package com.paawak.amex.demo.news.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.paawak.amex.demo.news.model.Article;
import com.paawak.amex.demo.news.service.NewsService;

@RestController
@RequestMapping(path = "/rest/news")
public class NewsRestController {

    private static final String CONTENT_TYPE_CSV = "text/csv";

    private final NewsService newsService;

    public NewsRestController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping
    public Map<String, String> uploadNewsBulletin(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("The file is empty!");
        }

        String contentType = file.getContentType();

        if (!contentType.equals(CONTENT_TYPE_CSV)) {
            throw new RuntimeException("The file should be of type CSV only");
        }

        Map<String, String> result = new HashMap<>();
        result.put("fileName", file.getOriginalFilename());
        result.put("contentType", contentType);

        int recordsInserted;
        try {
            recordsInserted = newsService.uploadRawNews(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("The file could not be uploaded", e);
        }

        result.put("recordsInserted", Integer.toString(recordsInserted));

        return result;

    }

    @GetMapping
    public List<Article> getArticles() {
        return newsService.getArticles();
    }

}
