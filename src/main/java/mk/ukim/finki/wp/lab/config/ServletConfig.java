package mk.ukim.finki.wp.lab.config;

import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService;
import mk.ukim.finki.wp.lab.web.BookListServlet;
import mk.ukim.finki.wp.lab.web.BookReservationServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<BookListServlet> bookListServletRegistration(BookService bookService) {
        ServletRegistrationBean<BookListServlet> registration = new ServletRegistrationBean<>(
                new BookListServlet(bookService), "/");
        registration.setName("BookListServlet");
        return registration;
    }

    @Bean
    public ServletRegistrationBean<BookReservationServlet> bookReservationServletRegistration(BookReservationService reservationService) {
        ServletRegistrationBean<BookReservationServlet> registration = new ServletRegistrationBean<>(
                new BookReservationServlet(reservationService), "/reservation", "/reservations");
        registration.setName("BookReservationServlet");
        return registration;
    }
}
