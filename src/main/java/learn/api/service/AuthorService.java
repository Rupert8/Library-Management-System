package learn.api.service;

import learn.api.dto.AuthorDto;
import learn.api.dto.BookDto;
import learn.api.dto.filters.BookFilter;
import learn.api.exception.AuthorNotFoundException;
import learn.api.exception.BookNotFoundException;
import learn.api.mapper.AuthorMapper;
import learn.api.mapper.BookMapper;
import learn.store.entities.Author;
import learn.store.entities.Book;
import learn.store.entities.Genre;
import learn.store.repository.AuthorRepository;
import learn.store.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorDto findById(Long id) {
        Optional<Author> book = authorRepository.findById(id);
        if (book.isPresent()) {
            return authorMapper.toAuthorDto(book.get());
        } else {
            throw new AuthorNotFoundException(String.format("book with id \"s%\" don't found", id));
        }
    }

}
