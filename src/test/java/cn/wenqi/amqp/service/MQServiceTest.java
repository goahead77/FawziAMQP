package cn.wenqi.amqp.service;

import cn.wenqi.amqp.config.RabbitConfiguration;
import cn.wenqi.amqp.config.TestBase;
import cn.wenqi.amqp.entity.MQEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wenqi
 */

public class MQServiceTest extends TestBase{

    private static final Log log= LogFactory.getLog(MQServiceTest.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void createOrder() throws JsonProcessingException {
        MQEntity mqEntity=new MQEntity();
        mqEntity.setJson(null);
        mqEntity.setObjType("Date");
        mqEntity.setMethod("select");
        mqEntity.setObjId(null);
        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(mqEntity);
        rabbitTemplate.convertAndSend(RabbitConfiguration.exchange,RabbitConfiguration.queueName,json);
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                System.out.println("confirm");
//            }
//        });
//        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
//            log.info(replyText);
//            log.info(replyCode);
//        });
    }

}
