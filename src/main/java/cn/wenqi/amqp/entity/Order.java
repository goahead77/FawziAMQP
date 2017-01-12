package cn.wenqi.amqp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wenqi
 */

@Setter
@Getter
@ToString
public class Order implements Serializable{

    private static final long serialVersionUID = -3702918577468552585L;
    /**
     * 订单ID
     */
    private String orderId;

    /**
     *  关联商品ID
     */
    private String goodsId;

}
