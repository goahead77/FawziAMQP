package cn.wenqi.amqp.service.impl;

import cn.wenqi.amqp.entity.MQModel;
import cn.wenqi.amqp.service.RabbitService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.SimpleResourceHolder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

/**
 * @author wenqi
 */

@Service
public class RabbitServiceImpl implements RabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String routKey="fawzi-sendEmail";

    @Override
    public void service(String vHost, String payload) {

        Message message= MessageBuilder.withBody("foo".getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setMessageId("123")
                .setHeader("foo","bar")
                .build();

        SimpleResourceHolder.bind(rabbitTemplate.getConnectionFactory(), vHost);
        rabbitTemplate.convertAndSend(message);
        SimpleResourceHolder.unbind(rabbitTemplate.getConnectionFactory());
    }

    @Override
    public void reply() {
        Object object=rabbitTemplate.receiveAndConvert("fawzi-sendEmail");
        System.out.println(object);
    }

    @Override
    public void send(Object message) {
        MQModel mqModel=new MQModel();
        mqModel.setId("id");
        mqModel.setType("email");
        mqModel.setContent(new ByteArrayInputStream("test content".getBytes()));
        rabbitTemplate.convertAndSend(routKey,mqModel);
    }

    @Override
    public Object receive() {
        return rabbitTemplate.receiveAndConvert(routKey);
    }

    @Override
    public void processOrder() {

    }

}
