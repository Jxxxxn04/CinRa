package com.example.cinra.data.service.showtime;

import com.example.cinra.data.responses.showtime.ShowtimeRequest;
import com.example.cinra.domain.entities.Showtime;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface ShowtimeService {

    Showtime addShowtime(ShowtimeRequest showtimeRequest) throws EntityNotFoundException;
    Showtime getShowtime(Long id) throws EntityNotFoundException;
    Showtime deleteShowtime(Long id) throws EntityNotFoundException;
    List<Showtime> getAllShowtimes();

}
