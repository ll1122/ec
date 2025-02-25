package com.eryansky.client.common.rpc;

import java.lang.annotation.*;

/**
 * 声明一个消费者注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RPCConsumer {

    /**
     * 服务地址
     */
    String serverUrl() default "";
}
