# Spring Boot Book Reservation Lab

A Spring Boot web application for book reservations with search functionality.

## Features

- **Book Management**: Display a list of books with title, genre, and average rating
- **Search Functionality**: Search books by title and filter by minimum rating
- **Reservation System**: Create book reservations with reader details
- **Responsive UI**: Clean HTML interface with CSS styling

## Project Structure

```
src/
├── main/
│   ├── java/mk/ukim/finki/wp/lab/
│   │   ├── bootstrap/
│   │   │   └── DataHolder.java          # Static data initialization
│   │   ├── config/
│   │   │   └── ServletConfig.java       # Servlet configuration
│   │   ├── model/
│   │   │   ├── Book.java                # Book entity
│   │   │   └── BookReservation.java     # Reservation entity
│   │   ├── repository/
│   │   │   ├── BookRepository.java      # Book repository interface
│   │   │   ├── BookReservationRepository.java
│   │   │   ├── InMemoryBookRepository.java
│   │   │   └── InMemoryBookReservationRepository.java
│   │   ├── service/
│   │   │   ├── BookService.java         # Book service interface
│   │   │   ├── BookServiceImpl.java
│   │   │   ├── BookReservationService.java
│   │   │   └── BookReservationServiceImpl.java
│   │   ├── web/
│   │   │   ├── BookListServlet.java     # Main page servlet
│   │   │   └── BookReservationServlet.java # Reservation servlet
│   │   └── WbLab1Application.java       # Spring Boot main class
│   └── resources/
│       ├── application.properties
│       └── templates/
│           ├── listBooks.html           # Book listing page
│           └── reservationConfirmation.html # Confirmation page
└── test/
    └── java/mk/ukim/finki/wp/lab/
        └── WbLab1ApplicationTests.java
```

## Technologies Used

- **Spring Boot 3.5.6**
- **Java 21**
- **Maven** for dependency management
- **Jakarta Servlets** for web layer
- **HTML/CSS** for frontend
- **Spring Dependency Injection**

## How to Run

1. **Prerequisites**:
   - Java 21 or higher
   - Maven 3.6 or higher

2. **Clone the repository**:
   ```bash
   git clone https://github.com/shopkoski/WD_LAB1.git
   cd WD_LAB1
   ```

3. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**:
   - Open your browser and go to `http://localhost:8080`
   - The application will display a list of books with search functionality
   - You can search by title and/or minimum rating
   - Select a book and fill in reservation details to make a reservation

## API Endpoints

- `GET /` - Book listing page with search functionality
- `POST /reservation` - Process book reservation
- `GET /reservation` - Process reservation (alternative)

## Sample Data

The application comes with 10 pre-loaded books:
- The Hobbit (Fantasy, 4.8)
- Clean Code (Programming, 4.6)
- 1984 (Dystopia, 4.4)
- Pride and Prejudice (Classic, 4.5)
- Sapiens (History, 4.2)
- To Kill a Mockingbird (Classic, 4.6)
- The Alchemist (Fiction, 4.0)
- The Pragmatic Programmer (Programming, 4.7)
- The Little Prince (Children, 4.3)
- Effective Java (Programming, 4.7)

## Features Implemented

✅ **Model Classes**: Book and BookReservation with proper getters/setters
✅ **Data Initialization**: Static data holder with 10 sample books
✅ **Repository Layer**: Interfaces and in-memory implementations
✅ **Service Layer**: Business logic with dependency injection
✅ **Web Layer**: Servlets for handling HTTP requests
✅ **Search Functionality**: Filter by title and minimum rating
✅ **Reservation System**: Complete reservation workflow
✅ **HTML Templates**: Responsive UI with CSS styling
✅ **Spring Configuration**: Proper servlet registration and dependency injection

## Lab Requirements Met

This project fulfills all the requirements from the Spring Boot lab exercise:
- Proper package structure (`mk.ukim.finki.wp.lab.*`)
- Model classes with specified fields
- Repository pattern implementation
- Service layer with dependency injection
- Servlet-based web layer
- Search functionality
- HTML template integration
- Spring Boot configuration

## Author

Created as part of the Web Programming lab course at UKIM FINKI.
