package learn.api.dto.filters;

import learn.store.entities.Author;
import learn.store.entities.Genre;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookFilter {
    private Author author;
    private Genre genre;
}
