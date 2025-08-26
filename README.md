# URL Shortener Service

A URL shortening service built with Spring Boot that allows you to convert long URLs into short, shareable links.

## ✨ Features

- Convert long URLs to short, memorable links
- Automatic redirection from short URLs to original URLs
- RESTful API for URL shortening
- PostgreSQL database for persistent storage
- Customizable short URL generation

## 🚀 Tech Stack

- **Backend Framework**: Spring Boot 3.x
- **Java Version**: 17+
- **Build Tool**: Maven
- **Database**: PostgreSQL
- **API Documentation**: SpringDoc OpenAPI
- **Testing**: JUnit 5, Mockito

## 📦 Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- PostgreSQL 12 or higher
- Git (for version control)

## 🛠️ Installation

1. **Set up PostgreSQL**:
   - Install PostgreSQL if you haven't already
   - Create a new database named `url_shortener`
   - Update the database credentials in `src/main/resources/application.properties` if needed

2. Clone the repository:
   ```bash
   git clone https://github.com/your-username/url-shortener.git
   cd url-shortener
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will be available at `http://localhost:8080`

## ⚙️ Configuration

Database configuration can be modified in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/url_shortener
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## 📚 API Documentation

Once the application is running, you can access:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **API Docs (JSON)**: `http://localhost:8080/v3/api-docs`


## 🧪 Running Tests

Run the test suite with:
```bash
mvn test
```

## 📊 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/urlshortener/
│   │       ├── controller/     # REST controllers
│   │       ├── model/          # Data models
│   │       ├── repository/     # Data access layer
│   │       ├── service/        # Business logic
│   │       ├── exception/      # Custom exceptions
│   │       └── config/         # Configuration classes
│   └── resources/
│       ├── static/             # Static resources
│       ├── templates/          # Thymeleaf templates
│       └── application.properties
└── test/                       # Test classes
```

## 🔧 Configuration

Edit `src/main/resources/application.properties` to configure:
- Server port
- Database settings
- URL shortener settings (length of short code, character set, etc.)

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## 🙏 Acknowledgments

- Built with [Spring Boot](https://spring.io/projects/spring-boot)
- Inspired by popular URL shorteners like Bitly and TinyURL

---

Made with ❤️ by Kalyani
