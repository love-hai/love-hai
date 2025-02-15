package com.loveSea.nettyShow.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiahaifeng
 */
@Data
public class InvokerProtocol implements Serializable {
    public String className;
    public String methodName;
    public Class<?>[] parames;
    public Object[] values;
}