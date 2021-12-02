package com.github.feigncustomerservice;

import com.github.feigncustomerservice.integration.CoffeeOrderService;
import com.github.feigncustomerservice.integration.CoffeeService;
import com.github.feigncustomerservice.model.Coffee;
import com.github.feigncustomerservice.model.CoffeeOrder;
import com.github.feigncustomerservice.model.NewOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class CustomerRunner implements ApplicationRunner {
    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        readMenu();
        Long id = orderCoffee();
        queryOrder(id);
    }

    private void readMenu() {
        List<Coffee> coffees = coffeeService.getAll();
        coffees.forEach(c -> log.info("Coffee : {}", c));
    }

    private Long orderCoffee() {
        NewOrderRequest orderRequest = NewOrderRequest.builder()
                .customer("Kim")
                .items(Arrays.asList("capucccino"))
                .build();
        CoffeeOrder order = coffeeOrderService.create(orderRequest);
        log.info("Order ID : {}", order.getId());
        return order.getId();
    }

    private void queryOrder(Long id) {
        CoffeeOrder order = coffeeOrderService.getOrder(id);
        log.info("Order : {}", order);
    }
}
