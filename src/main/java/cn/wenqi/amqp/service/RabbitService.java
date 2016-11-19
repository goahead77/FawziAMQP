package cn.wenqi.amqp.service;

/**
 * @author wenqi
 */
public interface RabbitService {

    void service(String vHost, String payload);

    void reply();
}
