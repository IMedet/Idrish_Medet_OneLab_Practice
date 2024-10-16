package kz.medet.repositories;

import kz.medet.model.Order;

public interface OrderRepository {
    Order findOrderById(Long id);
    void saveOrder(Order order);
    Order addOrder();
}
