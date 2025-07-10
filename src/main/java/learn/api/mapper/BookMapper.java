package learn.api.mapper;

import learn.api.dto.BookDto;
import learn.store.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto toBookDto(Book book);

    Book toBook(BookDto bookDto);

    List<BookDto> toBookDtoList(List<Book> books);
}
