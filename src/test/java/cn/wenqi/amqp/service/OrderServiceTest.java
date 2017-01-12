package cn.wenqi.amqp.service;

import cn.wenqi.amqp.config.RabbitConfiguration;
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

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void createOrder(){
        Order order=new Order();
        order.setGoodsId("good_"+ UUID.randomUUID().toString());
        order.setOrderId("order_"+UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConfiguration.exchange,RabbitConfiguration.orderQueueName,order);
    }

}
