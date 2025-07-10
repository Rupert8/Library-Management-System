package learn.store.entities;

import jakarta.persistence.*;
import learn.api.dto.BookDto;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"author", "genre"})
@Builder
@Entity
@Table(name = "books", schema = "library")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Author author;

    @Column(nullable = false)
    private String isbn;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Genre genre;

    @Column(name = "available_copies", nullable = false)
    private Long availableCopies;

    @Column(name = "total_Copies", nullable = false)
    private Long totalCopies;

    public void updateFromDto(BookDto bookDto) {
        if (bookDto.title() != null) {
            this.setTitle(bookDto.title());
        }
        if (bookDto.author() != null) {
            this.setAuthor(bookDto.author());
        }
        if (bookDto.isbn() != null) {
            this.setIsbn(bookDto.isbn());
        }
        if (bookDto.publicationDate() != null) {
            this.setPublicationDate(bookDto.publicationDate());
        }
        if (bookDto.genre() != null) {
            this.setGenre(bookDto.genre());
        }
        if (bookDto.availableCopies() != null) {
            this.setAvailableCopies(bookDto.availableCopies());
        }
        if (bookDto.totalCopies() != null) {
            this.setTotalCopies(bookDto.totalCopies());
        }
    }
}
