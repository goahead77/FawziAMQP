package cn.wenqi.amqp.entity;

import java.io.InputStream;

/**
 * @author wenqi
 */

public class MQModel {

    private String id;

    private String type;

    private InputStream content;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }
}
