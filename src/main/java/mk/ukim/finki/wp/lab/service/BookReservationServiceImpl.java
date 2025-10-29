package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class BookReservationServiceImpl implements BookReservationService{

    private final BookReservationRepository reservationRepository;

    public BookReservationServiceImpl(BookReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numOfCopies) {
        BookReservation reservation=new BookReservation(
                bookTitle,
                readerName,
                readerAddress,
                (long) numOfCopies
        );
        return reservationRepository.save(reservation);
    }
}
