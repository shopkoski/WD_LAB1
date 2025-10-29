package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class BookReservationServlet extends HttpServlet {
    private BookReservationService reservationService;

    public BookReservationServlet(BookReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public void init() {
        // Spring will inject the service via constructor
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReservation(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReservation(req, resp);
    }

    private void processReservation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        int numOfCopies = 1;
        try {
            String numCopiesParam = req.getParameter("numCopies");
            if (numCopiesParam != null && !numCopiesParam.isEmpty()) {
                numOfCopies = Integer.parseInt(numCopiesParam);
            }
        } catch (NumberFormatException ignored) {}

        // Validate required parameters
        if (bookTitle == null || bookTitle.isEmpty() ||
            readerName == null || readerName.isEmpty() ||
            readerAddress == null || readerAddress.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required reservation parameters");
            return;
        }

        String clientIP = req.getRemoteAddr();

        BookReservation reservation = reservationService.placeReservation(
                bookTitle, readerName, readerAddress, numOfCopies);

        InputStream templateStream = getClass().getClassLoader()
                .getResourceAsStream("templates/reservationConfirmation.html");

        if (templateStream == null) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Template reservationConfirmation.html not found in resources/templates");
            return;
        }

        String html = new String(templateStream.readAllBytes(), StandardCharsets.UTF_8);

        // Use empty string if any value is null to prevent NullPointerException
        String displayReaderName = reservation.getReaderName() != null ? reservation.getReaderName() : "";
        String displayClientIP = clientIP != null ? clientIP : "";
        String displayBookTitle = reservation.getBookTitle() != null ? reservation.getBookTitle() : "";
        String displayNumCopies = reservation.getNumberOfCopies() != null ? String.valueOf(reservation.getNumberOfCopies()) : "0";

        html = html.replace("<!-- Display reader name from servlet -->", displayReaderName)
                .replace("<!-- Display IP address from servlet -->", displayClientIP)
                .replace("<!-- Display book title from servlet -->", displayBookTitle)
                .replace("<!-- Display number of copies from servlet -->", displayNumCopies);

        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(html);
    }
}
