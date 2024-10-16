package kz.medet.repositories.impl;

import kz.medet.model.Order;
import kz.medet.repositories.OrderRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class OrderRepositoryImpl implements OrderRepository {

    private final Map<Long, Order> orderMap = new HashMap<>();

    private Long init_Id = 1L;

    @Override
    public Order addOrder() {
        Order order = Order.builder()
                .id(init_Id)
                .timeCreated(Timestamp.from(Instant.now()))
                .build();
        orderMap.put(init_Id, order);
        init_Id++;

        return order;
    }

    @Override
    public Order findOrderById(Long id) {
        return orderMap.get(id);
    }

    @Override
    public void saveOrder(Order order) {
        orderMap.put(order.getId(), order);
    }
}
