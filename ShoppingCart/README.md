# About

A full stack news bulletin project. This uses the in-memory H2 DB.

## Libraries used
 * Spring Boot
 * Spring JDBC
 * Flyway to manage SQL scripts
 * In-memory Database: H2
 * Maven
 * Bootstrap CSS
 * JQuery
 * JUnit
 * Mockito for mocking
 
## Code Coverage
	* ArticleDaoImplTest
	* NewsServiceImplTest 

# Landing page
	http://localhost:8080/news-bulletin

# H2 Console
	http://localhost:8080/news-bulletin/h2

# File Upload UI
	http://localhost:8080/news-bulletin/raw-news-upload.html

# Rest API
## File Upload	
	POST: http://localhost:8080/news-bulletin/rest/news/
## Fetch News Headlines
	GET: http://localhost:8080/news-bulletin/rest/news/
## Search News Headlines
	GET: http://localhost:8080/news-bulletin/rest/news/search/term-to-search	