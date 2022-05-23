package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Customer;

import java.util.List;

public interface CustomerRepository extends Repository<Customer, Integer>{
    List<Customer> getByCustomer(Customer customer);
}
