package learn;

import learn.api.dto.BookDto;
import learn.api.mapper.BookMapper;
import learn.api.service.BookService;
import learn.api.utils.HibernateUtil;
import learn.store.entities.Author;
import learn.store.entities.Genre;
import learn.store.repository.AuthorRepository;
import learn.store.repository.BookRepository;
import learn.store.repository.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
class FilterTest {

    @Test
    public void testFilter() {
        try (var session = HibernateUtil.getSession()) {
            session.beginTransaction();
            var bookMapper = BookMapper.INSTANCE;
            var bookRepository = new BookRepository(session);
            var bookService  = new BookService(bookRepository, bookMapper);

            var authorRepository = new AuthorRepository(session);
            var genreRepository = new GenreRepository(session);

            Author author = authorRepository.findById(1L).get();
            Genre genre = genreRepository.findById(1L).get();

            List<BookDto> filteredBooks = bookService.findByAuthorAndGenre(author,genre);

            log.info("all book by filter {}", filteredBooks);

        }
    }
}
