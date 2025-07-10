package learn.store.entities;

import jakarta.persistence.*;
import learn.store.extendedInfo.AuthorPersonInfo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "books")
@Builder
@Entity
@Table(name = "authors", schema = "library")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AuthorPersonInfo authorPersonInfo;

    @OneToMany(mappedBy = "author")
    @Builder.Default
    private List<Book> books = new ArrayList<>();
}
