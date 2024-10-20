package kz.medet.repositories.impl;

import kz.medet.model.Customer;
import kz.medet.repositories.CustomerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS customers " +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY, first_name VARCHAR(255), last_name VARCHAR(255))");
    }

    private Customer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException {
        return Customer.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("first_name"))
                .lastname(rs.getString("last_name"))
                .build();
    }

    @Override
    public Customer findCustomerById(Long id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToCustomer, id);
    }

    @Override
    public void saveCustomer(Customer customer) {
        String sql = "UPDATE customers SET first_name = ?, last_name = ? WHERE id = ?";
        jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastname(), customer.getId());
    }

    @Override
    public void addCustomer(String firstName, String lastName) {
        String sql = "INSERT INTO customers (first_name, last_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, firstName, lastName);
    }

    @Override
    public List<Customer> getAllCustomer() {
        String sql = "SELECT * FROM customers";
        return jdbcTemplate.query(sql, this::mapRowToCustomer);
    }
}
