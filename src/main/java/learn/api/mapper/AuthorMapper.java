package learn.api.mapper;

import learn.api.dto.AuthorDto;
import learn.api.dto.BookDto;
import learn.store.entities.Author;
import learn.store.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto toAuthorDto(Author author);

    Author toAuthor(AuthorDto authorDto);

    List<AuthorDto> toAuthorDtoList(List<Author> authors);
}
