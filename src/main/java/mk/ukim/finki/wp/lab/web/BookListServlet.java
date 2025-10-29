package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BookListServlet extends HttpServlet {

    private BookService bookService;

    public BookListServlet(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void init() {
        // Spring will inject the service via constructor
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchText = req.getParameter("searchText");
        String ratingText = req.getParameter("rating");
        Double minRating = null;

        if (ratingText != null && !ratingText.isEmpty()) {
            try {
                minRating = Double.parseDouble(ratingText);
            } catch (NumberFormatException ignored) {}
        }

        List<Book> books;
        if ((searchText == null || searchText.isEmpty()) && minRating == null) {
            books = bookService.listAll();
        } else {
            books = bookService.searchBooks(searchText, minRating);
        }

        InputStream templateStream = getClass()
                .getClassLoader()
                .getResourceAsStream("templates/listBooks.html");

        if (templateStream == null) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Template listBooks.html not found in resources/templates");
            return;
        }

        String html = new String(templateStream.readAllBytes(), StandardCharsets.UTF_8);

        // Preserve search parameters in the form
        String searchTextValue = searchText != null ? searchText : "";
        String ratingValue = ratingText != null && !ratingText.isEmpty() ? ratingText : "";
        
        html = html.replace("name=\"searchText\"", "name=\"searchText\" value=\"" + escapeHtml(searchTextValue) + "\"");
        html = html.replace("name=\"rating\"", "name=\"rating\" value=\"" + escapeHtml(ratingValue) + "\"");

        StringBuilder bookOptions = new StringBuilder();
        for (Book book : books) {
            bookOptions.append(String.format(
                    "<div class='book-option'>" +
                            "<input type='radio' name='bookTitle' value='%s' required> " +
                            "Title: %s, Genre: %s, Rating: %.1f</div>",
                    escapeHtml(book.getTitle()), escapeHtml(book.getTitle()), 
                    escapeHtml(book.getGenre()), book.getAverageRating()
            ));
        }

        html = html.replace("<!-- Display radio buttons for each book,\n             the value should be the book title\n             and the displayed text should be:\n             Title: <book_title>, Genre: <book_genre>, Rating: <average_rating> -->",
                bookOptions.toString());

        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(html);
    }

    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }
}
