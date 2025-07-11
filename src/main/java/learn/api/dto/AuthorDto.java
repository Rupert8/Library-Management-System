package learn.api.dto;

import learn.store.entities.Book;
import learn.store.extendedInfo.AuthorPersonInfo;

import java.util.ArrayList;
import java.util.List;

public record AuthorDto(
        AuthorPersonInfo authorPersonInfo,
        List<Book> books) {
}
