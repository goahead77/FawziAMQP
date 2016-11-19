package cn.wenqi.amqp.service;

/**
 * @author wenqi
 */
public interface AmqpService {
    void service(String vHost, String payload);
}
