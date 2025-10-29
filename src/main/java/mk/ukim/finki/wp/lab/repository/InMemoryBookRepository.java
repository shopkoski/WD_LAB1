package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookRepository implements BookRepository{
    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        String search=text == null ? "" : text.toLowerCase().trim();
        double minRating = rating == null ? 0.0 :rating;
        return DataHolder.books.stream()
                .filter(b->b.getTitle().toLowerCase().contains(search))
                .filter(b ->b.getAverageRating()>=minRating )
                .collect(Collectors.toList());
    }
}
