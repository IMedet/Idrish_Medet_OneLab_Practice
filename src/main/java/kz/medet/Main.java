package kz.medet;

import kz.medet.config.AppConfig;
import kz.medet.model.Customer;
import kz.medet.model.Order;
import kz.medet.services.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Service service = context.getBean(Service.class);

        Scanner in = new Scanner(System.in);
        boolean running = true;

        System.out.println("Hello !");
        while (running) {
            System.out.println("----------------------------------------------\n" +
                    "What you wanna do ?\n" +
                    "1 - Show All Customers\n" +
                    "2 - Add Customer\n" +
                    "3 - Add Order to Customer\n" +
                    "4 - Show Order of Customer\n" +
                    "5 - Add Product To Order\n" +
                    "6 - Show Product of an Order\n" +
                    "7 - Exit\n\n");

            System.out.println("Enter your choice: ");
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Customers: \n");
                    service.showALlCustomers().stream().forEach(customer ->
                            System.out.println("ID: " + customer.getId() + ", "
                                    + "FirstName: " + customer.getFirstName() + ", "
                                    + "LastName: " + customer.getLastname()));
                    break;
                case 2:
                    System.out.println("Enter Customer info below: \n");

                    in.nextLine();

                    System.out.println("Enter Customer firstName: ");
                    String firstName = in.nextLine();

                    System.out.println("Enter Customer lastName: ");
                    String lastName = in.nextLine();

                    service.addCustomer(firstName, lastName);

                    System.out.println("Customer added: ");
                    break;
                case 3:
                    System.out.println("Enter Id of customer: ");
                    Long customer_Id = in.nextLong();

                    service.addOrderToCustomer(customer_Id);

                    System.out.println("Order was created for Customer ID: " + customer_Id);
                    break;
                case 4:
                    System.out.println("Enter Id of Customer: ");
                    Long customerId = in.nextLong();

                    service.getOrdersOfCustomer(customerId).stream().forEach(order ->
                            System.out.println(order.toString()));
                    break;
                case 5:
                    System.out.println("Enter Order Id to add product: ");
                    Long orderId = in.nextLong();

                    in.nextLine();

                    System.out.println("Enter name of product: ");
                    String name = in.nextLine();

                    System.out.println("Enter price of product: ");
                    double price = in.nextDouble();

                    service.addProductToOrder(orderId, name, price);
                    break;
                case 6:
                    System.out.println("Enter orderId to see Products of Order: ");
                    Long order_Id = in.nextLong();

                    service.getProductsOfOrder(order_Id).stream().forEach(product ->
                            System.out.println(product.toString()));
                    break;
                case 7:
                    System.out.println("Exiting the app , Bye !");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again (");
            }
        }

        in.close();
        System.exit(0);
    }
}