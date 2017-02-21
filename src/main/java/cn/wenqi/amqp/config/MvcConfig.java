package cn.wenqi.amqp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author wenqi
 */

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
public class MvcConfig extends WebMvcConfigurerAdapter{

}
