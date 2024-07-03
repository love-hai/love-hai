package com.LoveSea.fengCore.retryable;

import javassist.CtClass;
import lombok.extern.slf4j.Slf4j;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.AdviceAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * asm修改字节码
 *
 * @author xiahaifeng
 */
@Slf4j
public class RetryMethodAsmModifier {
    String outputDirectory;
    static String RetryMethodBody = """
            {
                try{
                    com.LoveSea.fengCore.retryable.RetryManagementImpl.pushRetryCount(Integer.valueOf(-1));
                    int attempts = 0;
                    com.LoveSea.fengCore.retryable.REException reException = null;
                    do {
                        attempts++;
                        try {
                            com.LoveSea.fengCore.retryable.RetryManagementImpl.addRetryCount();
                            %s;
                        } catch (com.LoveSea.fengCore.retryable.REException e) {
                            reException = e;
                            System.out.println("重试：" + attempts + " 次，异常信息：" + e.getMessage());
                            try {
                                Thread.sleep(%dL);
                            } catch (InterruptedException ie) {
                                System.out.println("Thread sleep error");
                            }
                        }
                    } while (attempts < %d);
                    if (reException != null) {
                        throw new Exception(reException.getMessage(), reException.getCause());
                    }
                    throw new RuntimeException("重试次数用完");
                } finally {
                    // 弹出重试次数
                    com.LoveSea.fengCore.retryable.RetryManagementImpl.popRetryCount();
                }
            }
            """;

    public RetryMethodAsmModifier(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public void modifyMethod(List<RetryMethod> retryMethods) {
        // 根据classname 分组
        Map<String, List<RetryMethod>> retryMethodMap = retryMethods.stream().collect(Collectors.groupingBy(RetryMethod::getClassName));
        for (Map.Entry<String, List<RetryMethod>> entry : retryMethodMap.entrySet()) {
            String className = entry.getKey();
            String classPath = outputDirectory + "/" + className.replace(".", "/") + ".class";
            ClassReader classReader;
            try (InputStream is = new FileInputStream(classPath)) {
                classReader = new ClassReader(is);
            } catch (Exception e) {
                log.error("Error reading class " + className + ":" + e.getMessage());
                continue;
            }
            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
            // 创建自定义的 ClassVisitor
            ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM9, classWriter) {
                @Override
                public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                    MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
                    List<RetryMethod> methods = retryMethodMap.get(className);
                    for (RetryMethod retryMethod : methods) {
                        if (name.equals(retryMethod.getMethodName())) {
                            // 检查参数是否一样
                            for (String parameter : retryMethod.getParameters()) {
                            }
                            String originalMethodName = "original_" + name;
                            // 重命名原方法
                            methodVisitor = new AdviceAdapter(Opcodes.ASM9, methodVisitor, access, name, descriptor) {
                                @Override
                                protected void onMethodEnter() {
                                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, className, originalMethodName, descriptor, false);
                                }
                            };
                        }
                    }
                    return methodVisitor;
                }

                @Override
                public void visitEnd() {
                    for (RetryMethod retryMethod : retryMethodMap.get(className)) {
                        String originalMethodName = "original_" + retryMethod.getMethodName();
                        String newMethodBody = String.format(RetryMethodBody,
                                String.format(originalMethodName, getMethodParams(retryMethod.getParameters())),
                                retryMethod.getDelay(),
                                retryMethod.getMaxRetries());
                        // 创建新的方法，包含重试逻辑
                        MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC, retryMethod.getMethodName(), getMethodDescriptor(retryMethod), null, null);
                        mv.visitCode();
                        // 将 newMethodBody 插入到新方法中
                        insertMethodBody(mv, newMethodBody);
                        mv.visitMaxs(0, 0);
                        mv.visitEnd();
                    }
                    super.visitEnd();
                }
            };
            classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);

            // 写入文件
            try (FileOutputStream fos = new FileOutputStream(classPath)) {
                fos.write(classWriter.toByteArray());
            } catch (Exception e) {
                log.error("Error writing modified class " + classPath + ":" + e.getMessage());
            }
        }
    }

    private static void insertMethodBody(MethodVisitor mv, String newMethodBody) {
        // 插入 newMethodBody 到方法中
        // 这里需要将 newMethodBody 解析为具体的字节码指令并插入到 MethodVisitor 中
        // 示例代码，实际实现需要将 newMethodBody 转换为字节码
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn(newMethodBody);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    private static String getMethodParams(List<String> params) {
        // 生成方法参数字符串，用于 newMethodBody
        return String.join(", ", params);
    }

    private static String getMethodDescriptor(RetryMethod retryMethod) {
        // 根据 RetryMethod 的参数和返回值生成方法描述符
        // 示例代码，实际实现需要根据具体情况生成描述符
        return "(I)J";  // 假设方法接收一个 int 参数，返回一个 long 值
    }


}
