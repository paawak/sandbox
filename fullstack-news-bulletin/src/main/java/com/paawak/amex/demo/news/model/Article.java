package com.paawak.amex.demo.news.model;

import java.sql.Date;

/**
 * POJO to model the table <b>ARTICLE</b>
 * 
 * @author paawak
 *
 */
public class Article {

    private final long id;
    private final String headline;
    private final String url;
    private final String publisherName;
    private final String classifier;
    private final String publisherUrl;
    private final Date publishedTime;

    public Article() {
        this(0, null, null, null, null, null, null);
    }

    public Article(long id, String headline, String url, String publisherName, String classifier, String publisherUrl, Date publishedTime) {
        this.id = id;
        this.headline = headline;
        this.url = url;
        this.publisherName = publisherName;
        this.classifier = classifier;
        this.publisherUrl = publisherUrl;
        this.publishedTime = publishedTime;
    }

    public long getId() {
        return id;
    }

    public String getHeadline() {
        return headline;
    }

    public String getUrl() {
        return url;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getClassifier() {
        return classifier;
    }

    public String getPublisherUrl() {
        return publisherUrl;
    }

    public Date getPublishedTime() {
        return publishedTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classifier == null) ? 0 : classifier.hashCode());
        result = prime * result + ((headline == null) ? 0 : headline.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((publishedTime == null) ? 0 : publishedTime.hashCode());
        result = prime * result + ((publisherName == null) ? 0 : publisherName.hashCode());
        result = prime * result + ((publisherUrl == null) ? 0 : publisherUrl.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Article other = (Article) obj;
        if (classifier == null) {
            if (other.classifier != null)
                return false;
        } else if (!classifier.equals(other.classifier))
            return false;
        if (headline == null) {
            if (other.headline != null)
                return false;
        } else if (!headline.equals(other.headline))
            return false;
        if (id != other.id)
            return false;
        if (publishedTime == null) {
            if (other.publishedTime != null)
                return false;
        } else if (!publishedTime.equals(other.publishedTime))
            return false;
        if (publisherName == null) {
            if (other.publisherName != null)
                return false;
        } else if (!publisherName.equals(other.publisherName))
            return false;
        if (publisherUrl == null) {
            if (other.publisherUrl != null)
                return false;
        } else if (!publisherUrl.equals(other.publisherUrl))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Article [id=");
        builder.append(id);
        builder.append(", headline=");
        builder.append(headline);
        builder.append(", url=");
        builder.append(url);
        builder.append(", publisherName=");
        builder.append(publisherName);
        builder.append(", classifier=");
        builder.append(classifier);
        builder.append(", publisherUrl=");
        builder.append(publisherUrl);
        builder.append(", publishedTime=");
        builder.append(publishedTime);
        builder.append("]");
        return builder.toString();
    }

}
