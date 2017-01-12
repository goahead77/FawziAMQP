package cn.wenqi.amqp.service.impl;

import cn.wenqi.amqp.config.RabbitConfiguration;
import cn.wenqi.amqp.entity.Order;
import cn.wenqi.amqp.service.OrderService;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author wenqi
 */
@Service
public class OrderServiceImpl implements OrderService {

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitConfiguration.orderQueueName,durable = "true"),
            exchange = @Exchange(value = RabbitConfiguration.exchange,durable = "true"))
    )
    @Override
    public void processOrder(Order order) {
        System.out.println("开始处理订单...");
        System.out.println(order);
    }

    @RabbitListener(queues = RabbitConfiguration.orderQueueName)
    @Override
    public void analysisOrder(Order order){
        System.out.println("根据订单进行相应分析...");
        System.out.println(order);
    }
}
