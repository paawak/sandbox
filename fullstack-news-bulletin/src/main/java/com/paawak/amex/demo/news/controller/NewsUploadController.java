package com.paawak.amex.demo.news.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class NewsUploadController {

    private static final String CONTENT_TYPE_CSV = "text/csv";

    @PostMapping("/rest/raw-news/upload")
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

        return result;

    }

}
