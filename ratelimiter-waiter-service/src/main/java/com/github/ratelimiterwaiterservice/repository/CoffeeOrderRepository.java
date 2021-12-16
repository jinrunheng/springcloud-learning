package com.github.ratelimiterwaiterservice.repository;

import com.github.ratelimiterwaiterservice.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
