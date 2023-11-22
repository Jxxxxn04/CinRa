package com.example.cinra.data.service.booking;

import com.example.cinra.data.models.BookingRequest;
import com.example.cinra.data.repositories.BookingRepository;
import com.example.cinra.data.service.user.UserServiceImpl;
import com.example.cinra.domain.entities.Booking;
import com.example.cinra.domain.entities.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    BookingRepository bookingRepository;
    UserServiceImpl userService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, UserServiceImpl userService) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
    }

    @Override
    public void addBooking(BookingRequest bookingRequest) {
        // TODO : Add Showtime to the Parameters
        // TODO : Look wheather the user already has a Booking for the specific showtime

        User user = userService.getUser(bookingRequest.getUserID());
        Booking booking = new Booking(user, bookingRequest.getNumOfTickets());
        user.addBooking(booking);
        bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long id) throws EntityNotFoundException {

    }

    @Override
    public Booking getBooking(Long id) throws EntityNotFoundException {
        return bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + id));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
