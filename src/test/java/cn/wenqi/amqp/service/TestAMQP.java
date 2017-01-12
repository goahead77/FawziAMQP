package cn.wenqi.amqp.service;

import cn.wenqi.amqp.config.TestBase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wenqi
 */



public class TestAMQP extends TestBase {


    private final String port="5673";


    @Autowired
    private RabbitService rabbitService;

    private String sendEmailQueue="fawzi-sendEmail";

    @Test
    public void testSend(){
        rabbitService.send("这里是测试内容");
    }

    @Test
    public void testReceive(){
        System.out.println(rabbitService.receive());
    }
    private CachingConnectionFactory connectionFactory;
    private AmqpTemplate amqpTemplate;

    @Before
    public void initConnection(){
        connectionFactory = new CachingConnectionFactory("101.200.63.60");
        connectionFactory.setUsername("fawzi");
        connectionFactory.setPassword("fawzi77.");
        amqpTemplate=new RabbitTemplate(connectionFactory);
    }

    @Test
    public void send(){
        AmqpAdmin amqpAdmin=new RabbitAdmin(connectionFactory);
        amqpAdmin.declareQueue(new Queue(sendEmailQueue));
        amqpTemplate.convertAndSend(sendEmailQueue,"foo");
    }

    @Test
    public void receive(){
        String foo = (String) amqpTemplate.receiveAndConvert(sendEmailQueue);
        System.out.println(foo);
    }

}
