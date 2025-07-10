package learn.api.dto;

import learn.store.entities.Author;
import learn.store.entities.Genre;

import java.time.LocalDate;
import java.util.Calendar;

public record BookDto(
    Long id,
    String title,
    Author author,
    String isbn,
    LocalDate publicationDate,
    Genre genre,
    Long availableCopies,
    Long totalCopies) {
}
