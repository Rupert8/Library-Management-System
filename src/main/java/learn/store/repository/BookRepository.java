package learn.store.repository;

import jakarta.persistence.EntityManager;
import learn.store.entities.Book;

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
}
