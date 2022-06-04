package com.effitizer.start.service;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.Order;
import com.effitizer.start.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    /**
     * Order 저장
     */
    public Order saveOrder(Contents contents, int order_num) {
        Order order = new Order(order_num, contents);
        orderRepository.save(order);
        return order;
    }
}
