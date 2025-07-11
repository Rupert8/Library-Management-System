package learn.store.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import learn.api.dto.filters.BookFilter;
import learn.store.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository extends BasicRepository<Long, Book>{
    private final EntityManager entityManager;

    public BookRepository(EntityManager entityManager) {
        super(entityManager, Book.class);
        this.entityManager = entityManager;
    }

    public List<Book> findByName(String name) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteria = criteriaBuilder.createQuery(Book.class);
        var object = criteria.from(Book.class);
        criteria.select(criteria.from(Book.class))
                .where(criteriaBuilder.equal(object.get("title"), name));

        return entityManager.createQuery(criteria).getResultList();
    }

    public List<Book> findByAuthorAndGenre(BookFilter filter) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteria = criteriaBuilder.createQuery(Book.class);
        var object = criteria.from(Book.class);

        List<Predicate> predicates = new ArrayList<>();
        if(filter.getGenre() != null) {
            predicates.add(criteriaBuilder.equal(object.get("author"), filter.getAuthor()));
        }
        if(filter.getGenre() != null) {
            predicates.add(criteriaBuilder.equal(object.get("genre"), filter.getGenre()));
        }
        criteria.select(criteria.from(Book.class))
                .where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(criteria).getResultList();
    }
}
