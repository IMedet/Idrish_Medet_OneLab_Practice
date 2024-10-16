package kz.medet.repositories.impl;

import kz.medet.model.Customer;
import kz.medet.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerRepositoryImpl implements CustomerRepository {

    private final Map<Long, Customer> customerMap = new HashMap<>();

    private Long init_Id = 1L;

    @Override
    public Customer findCustomerById(Long id) {
        return customerMap.get(id);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerMap.put(customer.getId(), customer);
    }

    @Override
    public void addCustomer(String firstName, String lastName) {
        Customer customer = Customer.builder()
                .id(init_Id)
                .firstName(firstName)
                .lastname(lastName)
                .build();
        customerMap.put(init_Id, customer);
        init_Id++;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerMap.values().stream().toList();
    }
}
