package cn.wenqi.amqp.service.impl;

import cn.wenqi.amqp.entity.Order;
import cn.wenqi.amqp.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author wenqi
 */
@Service
public class OrderServiceImpl implements OrderService {

    static final String orderQueue="fawzi-order";

    @RabbitListener(queues = orderQueue)
    @Override
    public void processOrder(Order order) {
        System.out.println(order);
    }
}
