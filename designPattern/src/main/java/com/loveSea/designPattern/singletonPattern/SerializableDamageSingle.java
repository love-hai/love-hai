package com.loveSea.designPattern.singletonPattern;

import java.io.Serial;
import java.io.Serializable;

/**
 * 这种方法还是会实例化一个对象，
 * 可以使用枚举单例来避免序列化和反射破坏单例。
 * 不能通过反射来创建枚举类型。
 * @author xiahaifeng
 */

public class SerializableDamageSingle implements Serializable {
    private static final long serialVersionUID = -3549737897992231350L;
    private SerializableDamageSingle() {}
    private SerializableDamageSingle serializableDamageSingle;
    public SerializableDamageSingle getInstance() {
        return serializableDamageSingle;
    }

    @Serial
    private Object readResolve(){
        return getInstance();
    }
}