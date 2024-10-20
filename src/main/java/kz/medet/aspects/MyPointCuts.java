package kz.medet.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointCuts {

    @Pointcut("execution(void kz.medet.services.Service.addCustomer(..))")
    public void addCustomerMethod(){}

    @Pointcut("execution(* kz.medet.services.Service.showALlCustomers())")
    public void getCustomersMethod(){}

    @Pointcut("execution(* kz.medet.services.Service.addOrderToCustomer(..))")
    public void addOrderToCustomerMethod(){}

    @Pointcut("execution(* kz.medet.services.Service.addProductToOrder(..))")
    public void addProductToOrderMethod(){}

    @Pointcut("execution(* kz.medet.services.Service.getOrdersOfCustomer(..))")
    public void getOrderOfCustomerMethod(){}

    @Pointcut("execution(* kz.medet.services.Service.getProductsOfOrder(..))")
    public void getProductOfOrderMethod(){}
}
