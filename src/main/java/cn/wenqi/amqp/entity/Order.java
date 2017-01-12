package cn.wenqi.amqp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wenqi
 */

@Setter
@Getter
@ToString
public class Order {

    /**
     * 订单ID
     */
    private String orderId;

    /**
     *  关联商品ID
     */
    private String goodsId;

}
