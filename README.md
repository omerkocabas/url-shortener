![UrlShortener](https://github.com/user-attachments/assets/113a503c-67ce-471e-b4ce-d1d8d46eba20)

# Spring Boot, PostgreSQL, JPA, Rest API, Apache Kafka, Couchbase.

Url Shortener system with Analytics(stores number of clicks on URLs) and Cleanup Service(Deletes expired urls) using Spring Boot, PostgreSQL, JPA, Kafka, Couchbase.

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/omerkocabas/url-shortener.git
```

**2. Verify that Docker Desktop is installed on your PC. Go to MultiContainer directory in repository and run docker-compose up command.**
**Important Note:** If you experience "url-shortener/MultiContainer/initcouchbase.sh is not shared from the host and is not known to Docker." error, open your Docker Desktop and go to Preferences -> Resources -> File Sharing and add MultiContainer directory to list. Restart Docker Desktop.
```bash
cd url-shortener/MultiContainer
docker-compose up
```
![URL Shortener](https://github.com/user-attachments/assets/3d165427-5b62-44f2-8a73-1b8afbd36bbb)
