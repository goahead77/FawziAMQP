package cn.wenqi.amqp.service.impl;

import cn.wenqi.amqp.service.RabbitService;
import org.springframework.amqp.rabbit.connection.SimpleResourceHolder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wenqi
 */

@Service
public class RabbitServiceImpl implements RabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void service(String vHost, String payload) {

        SimpleResourceHolder.bind(rabbitTemplate.getConnectionFactory(), vHost);
        rabbitTemplate.convertAndSend(payload);
        SimpleResourceHolder.unbind(rabbitTemplate.getConnectionFactory());
    }

    @Override
    public void reply() {
        Object object=rabbitTemplate.receiveAndConvert("myqueue");
        System.out.println(object);
    }
}
