package cn.wenqi.amqp.service;

import cn.wenqi.amqp.config.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wenqi
 */



public class TestAMQP extends TestBase {

    @Autowired
    private RabbitService rabbitService;

    @Test
    public void testSend(){
        rabbitService.service("5672","Hello RabbitMQ");

        rabbitService.reply();
    }

}
