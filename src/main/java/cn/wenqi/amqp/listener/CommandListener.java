package cn.wenqi.amqp.listener;

import cn.wenqi.amqp.config.RabbitConfiguration;
import cn.wenqi.amqp.entity.MQEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author wenqi
 */

@Component
public class CommandListener {
    private static final Log log= LogFactory.getLog(CommandListener.class);

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory",bindings = @QueueBinding(
            value = @Queue(value = RabbitConfiguration.queueName,durable = "true"),
            exchange = @Exchange(value = RabbitConfiguration.exchange,durable = "true"))
    )
    @RabbitHandler
    @SendTo
    public MQEntity handle1(@Payload String json){
        log.info("开始处理请求...");
        log.info("接受到的json是："+json);
        MQEntity mqEntity=new MQEntity();
        mqEntity.setJson(null);
        mqEntity.setObjType("Date");
        mqEntity.setMethod("select");
        mqEntity.setObjId(null);
        return mqEntity;
//        if(StringUtils.isEmpty(json))
//            throw new IllegalArgumentException("参数不能为空");
//        try{
//            ObjectMapper objectMapper=new ObjectMapper();
//            MQEntity entity=objectMapper.readValue(json, MQEntity.class);
//            if(entity.getObjType().equals("Date")){
//                log.info("处理日期相关数据..");
//            }else {
//                log.info("处理"+entity.getObjType()+"的数据...");
//            }
//            return entity;
//        }catch (Exception e){
//            e.printStackTrace();
//            log.info("解析数据异常...");
//        }
//        return null;
    }

    @RabbitHandler
    public void handle2(int value){
        log.info("接收到整型："+value);
    }

}
