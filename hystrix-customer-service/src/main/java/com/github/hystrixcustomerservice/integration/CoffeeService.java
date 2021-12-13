package com.github.hystrixcustomerservice.integration;

import com.github.hystrixcustomerservice.model.Coffee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "waiter-service", contextId = "coffee",
        qualifier = "coffeeService", path = "/coffee",
        fallback = FallbackCoffeeService.class)
// 如果用了 Fallback，不要在接口上加 @RequestMapping 注解，path 可以用在这里
public interface CoffeeService {

    @GetMapping(path = "/")
    List<Coffee> getAll();

    @GetMapping("/{id}")
    Coffee getById(@PathVariable Long id);

    @GetMapping(path = "/", params = "name")
    Coffee getByName(@RequestParam String name);
}
