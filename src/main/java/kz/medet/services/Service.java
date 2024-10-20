package kz.medet.services;

import kz.medet.model.Customer;
import kz.medet.model.Order;
import kz.medet.model.Product;
import kz.medet.repositories.CustomerRepository;
import kz.medet.repositories.OrderRepository;
import kz.medet.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    Product globe_product = Product.builder().id(1L).name("Book").price(5000).build();
    Order globe_order = Order.builder().id(1L).timeCreated(Timestamp.from(Instant.now())).products(new ArrayList<>()).build();

    @Autowired
    public Service(CustomerRepository customerRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public void addCustomer(String firstName, String lastName) {
        customerRepository.addCustomer(firstName, lastName);
    }

    public void addOrderToCustomer(Long customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        Order order = orderRepository.addOrder();
        customer.getOrders().add(order);
        System.out.println(customer.getOrders());
        customerRepository.saveCustomer(customer);
        globe_order = order;
    }

    public void addProductToOrder(Long orderId, String name, double price) {
        Product product = productRepository.addProduct(name, price);
        Order order = orderRepository.findOrderById(orderId);
        order.getProducts().add(product);
        System.out.println(order.getProducts());
        orderRepository.saveOrder(order);
        globe_product = product;
    }

    public List<Customer> showALlCustomers() {
        return customerRepository.getAllCustomer();
    }

    public List<Order> getOrdersOfCustomer(Long customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        System.out.println(globe_order);
        return customerRepository.findCustomerById(customerId).getOrders();
    }

    public List<Product> getProductsOfOrder(Long orderId) {
        System.out.println(globe_product);
        return orderRepository.findOrderById(orderId).getProducts();
    }
}
