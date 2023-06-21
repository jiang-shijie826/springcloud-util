package com.jiang.constant;

/**
 * @author jiangsj
 * @create 2023/6/20
 * @desc
 */
public class RabbitMQConfig {
    /**
     * RabbitMQ的队列主题名称
     */
    public static final String RABBITMQ_DEMO_TOPIC = "rabbitmq.demo.topic";

    /**
     * RabbitMQ的DIRECT交换机名称
     */
    public static final String EMAIL_EXCHANGE = "email_exchange";

    /**
     * RabbitMQ的DIRECT交换机和队列绑定的匹配键 DirectRouting
     */
    public static final String RABBITMQ_DEMO_DIRECT_ROUTING = "rabbitmq.demo.direct.routing";

}
