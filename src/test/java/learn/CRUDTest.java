package learn;

import learn.api.mapper.BookMapper;
import learn.api.service.BookService;
import learn.api.utils.HibernateUtil;
import learn.store.entities.Author;
import learn.store.entities.Book;
import learn.store.entities.Genre;
import learn.store.extendedInfo.AuthorPersonInfo;
import learn.store.extendedInfo.Gender;
import learn.store.repository.AuthorRepository;
import learn.store.repository.BookRepository;
import learn.store.repository.GenreRepository;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@Slf4j
class CRUDTest {

    @Test
    public void testSaveBook(){
        @Cleanup var session = HibernateUtil.getSession();
        session.beginTransaction();

        AuthorRepository authorRepository = new AuthorRepository(session);
        GenreRepository genreRepository = new GenreRepository(session);

        log.info("початок транзакції");

        AuthorPersonInfo personInfo = AuthorPersonInfo.builder()
                .firstName("Маргарет")
                .lastName("Етвуд")
                .birthDate(LocalDate.of(1939, 11, 18))
                .biography("Канадська письменниця, поетеса, літературна критикиня, лауреатка Букерівської премії...")
                .gender(Gender.FEMALE)
                .build();

        Author author = Author.builder()
                .authorPersonInfo(personInfo)
                .build();
        authorRepository.save(author);

        Genre genre = Genre.builder()
                .name("Антиутопія")
                .build();

        genreRepository.save(genre);

        Book book = Book.builder()
                .title("1984")
                .author(author)
                .isbn("978-0451524935")
                .publicationDate(LocalDate.of(1949,8, 8))
                .genre(genre)
                .availableCopies(50L)
                .totalCopies(100L)
                .build();

        session.persist(book);

        log.info("name info about book: {}", book);
    session.getTransaction().commit();
    }

    @Test
    public void testFindByNameBook(){
        try(var session = HibernateUtil.getSession()){
            session.beginTransaction();

            var bookMapper = BookMapper.INSTANCE;
            var bookRepository = new BookRepository(session);
            var bookService = new BookService(bookRepository, bookMapper);


            var books = bookService.findByName("1984");
            log.info("book {}", books);
            session.getTransaction().commit();
        }
    }

    @Test
    public void testUpdateBook(){
        try(var session = HibernateUtil.getSession()){
            session.beginTransaction();
            var bookMapper = BookMapper.INSTANCE;
            var bookRepository = new BookRepository(session);
            var bookService = new BookService(bookRepository, bookMapper);

            Book book = Book.builder()
                    .totalCopies(110L)
                    .build();

            bookService.update(1L,bookMapper.toBookDto(book));

            session.getTransaction().commit();
        }catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void testDeleteBook(){
//        try(var session = HibernateUtil.getSession()){
//            session.beginTransaction();
//            var bookMapper = BookMapper.INSTANCE;
//            var bookRepository = new BookRepository(session);
//            var bookService = new BookService(bookRepository, bookMapper);
//
//            System.out.println(bookService.delete(1L));
//
//            session.getTransaction().commit();
//        }
//    }
}