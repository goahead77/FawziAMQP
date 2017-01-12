package cn.wenqi.amqp.service;

/**
 * @author wenqi
 */
public interface RabbitService {

    @Deprecated
    void service(String vHost, String payload);

    @Deprecated
    void reply();

    void send(Object message);

    Object receive();

    void processOrder();
}
