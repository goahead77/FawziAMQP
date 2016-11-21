package cn.wenqi.amqp.service;

import cn.wenqi.amqp.config.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wenqi
 */



public class TestAMQP extends TestBase {


    private final String port="5673";


    @Autowired
    private RabbitService rabbitService;


    @Test
    public void testSend(){
        rabbitService.service(port,"Hello RabbitMQ");

        rabbitService.reply();
    }

}
