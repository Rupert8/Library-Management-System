package learn.api.service;

import learn.api.exception.BookNotFoundException;
import learn.api.dto.BookDto;
import learn.api.mapper.BookMapper;
import learn.store.repository.BookRepository;
import learn.store.entities.Book;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDto findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return bookMapper.toBookDto(book.get());
        } else {
            throw new BookNotFoundException(String.format("book with id \"s%\" don't found", id));
        }
    }

    public BookDto save(Book book) {
        if (book != null) {
            bookRepository.save(book);
            return bookMapper.toBookDto(book);
        } else {
            throw new BookNotFoundException("book is empty");
        }
    }

    public BookDto update(Long id, BookDto bookDto) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(()-> new BookNotFoundException(String.format("book with id \"s%\" don't found", id)));

        book.updateFromDto(bookDto);

        bookRepository.update(book);
        return bookMapper.toBookDto(book);

    }

    public List<BookDto> findByName(String name) {
        List<Book> books = bookRepository.findByName(name);
        return bookMapper.toBookDtoList(books);
    }
}
