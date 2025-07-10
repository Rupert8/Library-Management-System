package learn.store.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<K extends Serializable, E> {
    void save(E e);

    void delete(K id);

    Optional<E> findById(K id);

    List<E> findAll();

    void update(E e);
}
