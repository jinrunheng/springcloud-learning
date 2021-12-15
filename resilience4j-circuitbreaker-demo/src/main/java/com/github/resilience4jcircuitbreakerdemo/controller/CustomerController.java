package com.github.resilience4jcircuitbreakerdemo.controller;

import com.github.resilience4jcircuitbreakerdemo.integration.CoffeeOrderService;
import com.github.resilience4jcircuitbreakerdemo.integration.CoffeeService;
import com.github.resilience4jcircuitbreakerdemo.model.Coffee;
import com.github.resilience4jcircuitbreakerdemo.model.CoffeeOrder;
import com.github.resilience4jcircuitbreakerdemo.model.NewOrderRequest;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerOpenException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

    @Resource
    private CoffeeService coffeeService;

    @Resource
    private CoffeeOrderService coffeeOrderService;

    private CircuitBreaker circuitBreaker;

    // 对应到配置文件中的 resilience4j.circuitbreaker.backends.menu
    public CustomerController(CircuitBreakerRegistry registry) {
        circuitBreaker = registry.circuitBreaker("menu");
    }

    // 编程方式实现熔断保护
    // 对应到配置文件中的 resilience4j.circuitbreaker.backends.menu
    @GetMapping("/menu")
    public List<Coffee> readMenu() {
        return Try.ofSupplier(
                CircuitBreaker.decorateSupplier(circuitBreaker, () -> coffeeService.getAll()))
                .recover(CircuitBreakerOpenException.class, Collections.emptyList())
                .get();
    }

    // 注解方式实现熔断保护
    // 对应到配置文件中的 resilience4j.circuitbreaker.backends.order
    @PostMapping("/order")
    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "order")
    public CoffeeOrder createOrder() {
        NewOrderRequest orderRequest = NewOrderRequest.builder()
                .customer("Kim")
                .items(Arrays.asList("capuccino"))
                .build();
        CoffeeOrder order = coffeeOrderService.create(orderRequest);
        log.info("Order ID : {}", order != null ? order.getId() : "-");
        return order;
    }
}
