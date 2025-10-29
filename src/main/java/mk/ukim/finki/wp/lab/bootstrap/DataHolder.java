package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;

import java.util.ArrayList;
import java.util.List;

public class DataHolder {
    public static List<Book> books= new ArrayList<>();
    public static List<BookReservation> reservations=new ArrayList<>();

    static {
        books.add(new Book("The Hobbit","Fantasy",4.8));
        books.add(new Book("Clean Code", "Programming", 4.6));
        books.add(new Book("1984", "Dystopia", 4.4));
        books.add(new Book("Pride and Prejudice", "Classic", 4.5));
        books.add(new Book("Sapiens", "History", 4.2));
        books.add(new Book("To Kill a Mockingbird", "Classic", 4.6));
        books.add(new Book("The Alchemist", "Fiction", 4.0));
        books.add(new Book("The Pragmatic Programmer", "Programming", 4.7));
        books.add(new Book("The Little Prince", "Children", 4.3));
        books.add(new Book("Effective Java", "Programming", 4.7));
    }
}
