package kz.medet.repositories;

import kz.medet.model.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer findCustomerById(Long id);
    void addCustomer(String firstName, String lastName);
    List<Customer> getAllCustomer();
    void saveCustomer(Customer customer);
}
