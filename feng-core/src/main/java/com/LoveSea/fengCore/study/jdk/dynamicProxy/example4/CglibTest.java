package com.LoveSea.fengCore.study.jdk.dynamicProxy.example4;

import com.LoveSea.fengCore.study.jdk.dynamicProxy.example1.Customer;
import com.LoveSea.fengCore.study.jdk.dynamicProxy.example1.Person;
import net.sf.cglib.core.DebuggingClassWriter;

/**
 * @author xiahaifeng
 */

public class CglibTest {
    // jvm 参数
    // --add-opens java.base/java.lang=ALL-UNNAMED
    public static void main(String[] args) {
        try {
   /*         String path = Customer.class.getResource("").getPath();
            path += "cglib_proxy_class/";
            System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,path);*/
            Person obj = (Person) new CglibMeipo().getInstance(Customer.class);
            obj.findLove();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}