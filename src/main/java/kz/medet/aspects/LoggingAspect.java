package kz.medet.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("kz.medet.aspects.MyPointCuts.addCustomerMethod()")
    public void beforeAddCustomerLoggingAdvice(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println("--------------------------------------------------------");
        logger.info("beforeAddCustomerLoggingAdvice: логирование перед добавлением customer\n" +
                "methodSignature.getName() = " + methodSignature.getName());
        System.out.println("--------------------------------------------------------");
    }

    @Before("kz.medet.aspects.MyPointCuts.getOrderOfCustomerMethod()")
    public void beforeGetOrderOfCustomerLoggingAdvice(){
        System.out.println("--------------------------------------------------------");
        logger.info("beforeGetOrderOfCustomerLoggingAdvice: логируем перед получением Order");
        System.out.println("--------------------------------------------------------");
    }

    @Before("kz.medet.aspects.MyPointCuts.getProductOfOrderMethod()")
    public void beforeGetProductOfOrderLoggingAdvice(){
        System.out.println("--------------------------------------------------------");
        logger.info("beforeGetProductOfOrderLoggingAdvice: логируем перед получением Product");
        System.out.println("--------------------------------------------------------");
    }

    @After("kz.medet.aspects.MyPointCuts.getCustomersMethod()")
    public void afterShowCustomerLoggingAdvice(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println("--------------------------------------------------------");
        logger.info("afterShowCustomerLoggingAdvice: логирования получение всех customer\n" +
                "methodSignature.getName() = " + methodSignature.getName());
        System.out.println("--------------------------------------------------------");
    }

    @Around("kz.medet.aspects.MyPointCuts.addOrderToCustomerMethod()")
    public void aroundAddOrderToCustomerLoggingAdvice(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        logger.info("aroundAddOrderToCustomerLoggingAdvice: пытаются добавить Order " +
                "для Customer ");
        System.out.println("--------------------------------------------------------");

        try{
            proceedingJoinPoint.proceed();
        }catch (Exception e){
            System.out.println("aroundAddOrderToCustomerLoggingAdvice: было поймано исключение " + e);
        }

        System.out.println("--------------------------------------------------------");
        logger.info("aroundAddOrderToCustomerLoggingAdvice: Order был успешно добавлен");
    }

    @Around("kz.medet.aspects.MyPointCuts.addProductToOrderMethod()")
    public void aroundAddProductToOrderLoggingAdvice(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        logger.info("aroundAddProductToOrderLoggingAdvice: пытаются добавить Product " +
                "для Order ");
        System.out.println("--------------------------------------------------------");

        try{
            proceedingJoinPoint.proceed();
        }catch (Exception e){
            System.out.println("aroundAddProductToOrderLoggingAdvice: было поймано исключение " + e);
        }

        System.out.println("--------------------------------------------------------");
        logger.info("aroundAddProductToOrderLoggingAdvice: Product был успешно добавлен");
    }


}
