package cn.wenqi.amqp.config;

import cn.wenqi.amqp.service.OrderService;
import cn.wenqi.amqp.service.impl.OrderServiceImpl;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collections;

/**
 * @author wenqi
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableRabbit
public class RabbitConfiguration {

    /**
     * Listing permissions in vhost "/" ...
        fawzi	^fawzi-.*	.*	.*
     */
    public final static String queueName="fawzi-sendEmail";

    public final static String orderQueueName="fawzi-order";

    public final static String exchange="fawzi-direct-exchange";


    @Autowired
    private OrderService orderService;

//    @Configuration
//    static class RabbitListener{
//
//        @Bean
//        public OrderService orderService(){
//            return new OrderServiceImpl();
//        }
//
//    }
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost("101.200.63.60");
        cachingConnectionFactory.setUsername("xx");
        cachingConnectionFactory.setPassword("xx");
        cachingConnectionFactory.setPublisherReturns(true);
        cachingConnectionFactory.setPublisherConfirms(true);
        cachingConnectionFactory.setChannelCacheSize(50);
        return cachingConnectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue testQueue() {
        return new Queue(queueName);
    }

    @Bean
    public Queue orderQueue(){
        return new Queue(orderQueueName);
    }

//    public RabbitConnectionFactoryBean clientConnectionFactory() {
//        RabbitConnectionFactoryBean rabbitConnectionFactoryBean = new RabbitConnectionFactoryBean();
//        rabbitConnectionFactoryBean.setUseSSL(true);
//        Resource resource = new PathResource("file:/secrets/rabbitSSL.properties");
//        rabbitConnectionFactoryBean.setSslPropertiesLocation(resource);
//        return rabbitConnectionFactoryBean;
//    }

    @Bean
    public AmqpTemplate retryRabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(500);
        backOffPolicy.setMultiplier(10.0);
        backOffPolicy.setMaxInterval(10000);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        template.setRetryTemplate(retryTemplate);
        return template;
    }


    @Bean
    public RabbitTransactionManager rabbitTxManager(){
        return new RabbitTransactionManager(connectionFactory());
    }

    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        factory.setMessageConverter(messageConverter());
        return factory;
    }

    @Bean
    MessageConverter messageConverter(){
        MessageConverter messageConverter=new SerializerMessageConverter();
        return messageConverter;
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames(queueName,orderQueueName);
        //container.setMessageListener(new MessageListenerAdapter(orderService));
        container.setConsumerArguments(Collections.singletonMap("x-priority", Integer.valueOf(10)));
        return container;
    }

//    @Bean
//    public MessageListener exampleListener() {
//        return message -> {
//                        byte[] body=message.getBody();
//            Object o=SerializationUtils.deserialize(body);
//            if(o instanceof MQModel){
//                MQModel mq= (MQModel) o;
//                System.out.println(mq.getId());
//            }
//            System.out.println(message);
//        };
//    }


    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding(Queue orderQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(orderQueue).to(directExchange).with(orderQueueName);
    }
}
