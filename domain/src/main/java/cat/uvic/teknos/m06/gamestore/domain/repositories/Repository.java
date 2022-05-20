package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Customer;

import java.util.List;

public interface Repository<TModel, Key> {
    void save(TModel model );
    void delete(TModel model);
    TModel getById(Key id);
    List<TModel> getAll();
}