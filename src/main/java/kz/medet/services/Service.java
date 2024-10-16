package kz.medet.services;

import kz.medet.model.Customer;
import kz.medet.model.Order;
import kz.medet.model.Product;
import kz.medet.repositories.CustomerRepository;
import kz.medet.repositories.OrderRepository;
import kz.medet.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public Service(CustomerRepository customerRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public void addCustomer(String firstName, String lastName){
        customerRepository.addCustomer(firstName, lastName);
    }

    public void addOrderToCustomer(Long customerId){
        Customer customer = customerRepository.findCustomerById(customerId);
        Order order = orderRepository.addOrder();
        customer.getOrders().add(order);
        customerRepository.saveCustomer(customer);
    }

    public void addProductToOrder(Long orderId, String name, double price){
        Product product = productRepository.addProduct(name, price);
        Order order = orderRepository.findOrderById(orderId);
        order.getProducts().add(product);
        orderRepository.saveOrder(order);
    }

    public List<Customer> showALlCustomers(){
        return customerRepository.getAllCustomer();
    }

    public List<Order> getOrdersOfCustomer(Long customerId){
        return customerRepository.findCustomerById(customerId).getOrders();
    }

    public List<Product> getProductsOfOrder(Long orderId){
        return orderRepository.findOrderById(orderId).getProducts();
    }
}
