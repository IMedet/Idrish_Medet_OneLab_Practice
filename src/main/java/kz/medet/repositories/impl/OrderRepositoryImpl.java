package kz.medet.repositories.impl;

import kz.medet.model.Order;
import kz.medet.repositories.OrderRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS orders (id BIGINT AUTO_INCREMENT PRIMARY KEY, time_created TIMESTAMP)");
    }

    @Override
    public Order addOrder() {
        String sql = "INSERT INTO orders (time_created) VALUES (?)";
        jdbcTemplate.update(sql, Timestamp.from(Instant.now()));

        String selectLastInsertIdSql = "SELECT MAX(time_created) AS max_time_created FROM orders";
        Timestamp lastInsertedTime = jdbcTemplate.queryForObject(selectLastInsertIdSql, Timestamp.class);


        String selectOrderByCreateTimeSql = "SELECT * FROM orders WHERE time_created = ?";
        Order order = jdbcTemplate.queryForObject(selectOrderByCreateTimeSql, this::mapRowToOrder, lastInsertedTime);
        return order;
    }

    @Override
    public Order findOrderById(Long id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToOrder, id);
    }

    @Override
    public void saveOrder(Order order) {
        String sql = "UPDATE orders SET time_created = ? WHERE id = ?";
        jdbcTemplate.update(sql, order.getTimeCreated(), order.getId());
    }

    private Order mapRowToOrder(ResultSet rs, int rowNum) throws SQLException {
        return Order.builder()
                .id(rs.getLong("id"))
                .timeCreated(rs.getTimestamp("time_created"))
                .build();
    }
}
