package learn.store.repository;

import jakarta.persistence.EntityManager;
import learn.store.entities.Author;
import learn.store.entities.Genre;

public class GenreRepository extends BasicRepository<Long, Genre>{
    public GenreRepository(EntityManager entityManager) {
        super(entityManager, Genre.class);
    }
    
}
