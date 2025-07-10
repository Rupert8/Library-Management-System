package learn.store.repository;

import jakarta.persistence.EntityManager;
import learn.store.entities.Author;
import learn.store.entities.Book;

import java.util.List;

public class AuthorRepository extends BasicRepository<Long, Author>{
    public AuthorRepository(EntityManager entityManager) {
        super(entityManager, Author.class);
    }
    
}
