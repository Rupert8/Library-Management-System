package learn.store.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BasicRepository<K extends Serializable, E> implements Repository<K, E> {
    private final EntityManager entityManager;
    private final Class<E> entityClass;

    @Override
    public void save(E e) {
        entityManager.persist(e);
    }

    @Override
    public void delete(K id) {
        entityManager.remove(entityManager.find(entityClass, id));
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public List<E> findAll() {
        var criteria = entityManager.getCriteriaBuilder().createQuery(entityClass);
        criteria.from(entityClass);
        return entityManager.createQuery(criteria).getResultList();
    }


    @Override
    public void update(E e) {
        entityManager.merge(e);
    }
}
