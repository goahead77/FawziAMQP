package cn.wenqi.amqp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用于接受MQ消息
 * @author wenqi
 */
@Setter
@Getter
@NoArgsConstructor
public class MQEntity implements Serializable{

    private static final long serialVersionUID = -363960565206394421L;
    /**
     * 操作实体类型 email
     */
    private String objType;

    /**
     * 操作实体ID
     */
    private String objId;

    /**
     * 操作方法  比如：add,update,delete
     */
    private String method;

    /**
     * object对应的json
     */
    private String json;

}
