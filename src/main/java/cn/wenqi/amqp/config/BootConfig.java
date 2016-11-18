package cn.wenqi.amqp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author wenqi
 */

@Configuration
@ComponentScan("cn.fawzi.thymeleaf")
@Import({MvcConfig.class})
public class BootConfig {

}
