package com.LoveSea.fengCore.study.jdk.dynamicProxy.example3;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiahaifeng
 */

public class GPProxy {
    public final static String In = "\r\n";

    public static Object newProxyInstance(GPClassLoader classLoader, Class<?>[] interfaces, GPInvocationHandler h) {
        // 动态生成源代码.java文件
        try {
            String src = generateSrc(interfaces);
            // 从java文件输出到磁盘
            String filePath = GPProxy.class.getResource("").getPath();
            File f = new File(filePath + "Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();
            // 把生成的.java文件编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(f.getAbsolutePath());
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);
            task.call();
            fileManager.close();
            // 把编译完生成的.class 文件加载到JVM中
            Class<?> proxyClass = classLoader.findClass("Proxy0");
            Constructor<?> constructor = proxyClass.getConstructor(GPInvocationHandler.class);
            f.delete();
            return constructor.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();
        sb.append("package com.LoveSea.fengCore.study.jdk.dynamicProxy.example3;" + In);
        sb.append("import com.LoveSea.fengCore.study.jdk.dynamicProxy.example1.Person;" + In);
        sb.append("import java.lang.reflect.*;" + In);
        sb.append("public class Proxy0 implements " + interfaces[0].getName() + "{" + In);
        sb.append("GPInvocationHandler h;" + In);
        sb.append("public Proxy0(GPInvocationHandler h) {" + In);
        sb.append("this.h = h;" + In);
        sb.append("}" + In);
        for (Method m : interfaces[0].getMethods()) {
            Class<?>[] parameterTypes = m.getParameterTypes();
            StringBuilder paramNames = new StringBuilder();
            StringBuilder paramValues = new StringBuilder();
            StringBuilder paramClasses = new StringBuilder();
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> clazz = parameterTypes[i];
                String type = clazz.getName();
                String methodName = toLowerFirstCase(m.getDeclaringClass().getSimpleName());
                paramNames.append(type + " " + methodName);
                paramValues.append(paramNames);
                paramClasses.append(type + ".class");
                if (i > 0 && i < parameterTypes.length - 1) {
                    paramNames.append(",");
                    paramValues.append(",");
                    paramClasses.append(",");
                }
            }
            sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(" + paramNames + "){" + In);
            sb.append("try {" + In);
            sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{" + paramClasses + "});" + In);
            sb.append(hasReturnValue(m.getReturnType()) ? "return " : "" + getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValues + "})", m.getReturnType()) + ";" + In);
            sb.append("}catch(Error _ex) {}");
            sb.append("catch (Throwable e) {" + In);
            sb.append("throw new UndeclaredThrowableException(e);" + In);
            sb.append("}" + In);
            sb.append(getReturnEmptyCode(m.getReturnType()));
            sb.append("}" + In);
        }
        sb.append("}" + In);
        return sb.toString();
    }

    public static Map<Class, Class> mappings = new HashMap<Class, Class>();

    static {

        mappings.put(int.class, Integer.class);
    }

    private static String getReturnEmptyCode(Class<?> returnType) {
        if (mappings.containsKey(returnType)) {
            return "return 0";
        } else if (returnType == void.class) {
            return "";
        } else {
            return "return null;";
        }
    }

    private static String getCaseCode(String code, Class<?> returnType) {
        if (mappings.containsKey(returnType)) {
            return "((" + mappings.get(returnType).getName() + ")" + code + ")." + returnType.getSimpleName() + "Value()";
        }
        return code;
    }

    public static boolean hasReturnValue(Class<?> returnType) {
        return void.class != returnType;
    }

    public static String toLowerFirstCase(String s) {
        char c = s.charAt(0);
        if (Character.isLowerCase(c)) {
            return s;
        } else {
            return Character.toLowerCase(c) + s.substring(1);
        }
    }
}