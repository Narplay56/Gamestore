package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Customer;

import java.util.List;

public class JpaCustomerRepository implements CustomerRepository{

    @Override
    public void save(Customer model) {

    }

    @Override
    public void delete(Customer model) {

    }

    @Override
    public Customer getById(Integer id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public List<Customer> getByCustomer(Customer customer) {
        return null;
    }
}
