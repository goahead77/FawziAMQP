package cn.wenqi.amqp.service;

import cn.wenqi.amqp.config.TestBase;
import cn.wenqi.amqp.entity.Order;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author wenqi
 */

public class OrderServiceTest extends TestBase{

    private static final String orderQueue="fawzi-order";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderService orderService;

    @Test
    public void processOrder(){
        orderService.processOrder(null);
    }

    @Test
    public void createOrder(){
        Order order=new Order();
        order.setGoodsId("good_"+ UUID.randomUUID().toString());
        order.setOrderId("order_"+UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(orderQueue,order);
    }

}
