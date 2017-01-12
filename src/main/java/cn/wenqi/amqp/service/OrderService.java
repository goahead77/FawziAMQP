package cn.wenqi.amqp.service;

import cn.wenqi.amqp.entity.Order;

/**
 * @author wenqi
 */
public interface OrderService {

    void processOrder(Order order);

    void analysisOrder(Order order);
}
