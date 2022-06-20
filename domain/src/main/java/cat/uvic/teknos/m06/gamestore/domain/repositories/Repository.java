package cat.uvic.teknos.m06.gamestore.domain.repositories;

import java.util.List;

public interface Repository<T, K> {
    void save(T model );
    void delete(K model);
    T getById(K id);
    List<T> getAll();
}
