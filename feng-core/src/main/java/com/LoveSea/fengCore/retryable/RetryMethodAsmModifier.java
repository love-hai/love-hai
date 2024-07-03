package com.LoveSea.fengCore.retryable;

import javassist.CtClass;
import lombok.extern.slf4j.Slf4j;
import org.objectweb.asm.*;

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
                    return new MethodVisitor(Opcodes.ASM9, methodVisitor) {
                        @Override
                        public void visitCode() {
                            super.visitCode();
                            // 插入打印语句
                            super.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                            super.visitLdcInsn("Hello from ASM!");
                            super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                        }
                    };
                }
            };
            classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);
            // 获取修改后的字节码
            byte[] modifiedClassBytes = classWriter.toByteArray();
            // 写入文件
            try (FileOutputStream fos = new FileOutputStream(classPath)) {
                fos.write(modifiedClassBytes);
            } catch (Exception e) {
                log.error("Error writing modified class " + classPath + ":" + e.getMessage());
            }
        }
    }


}
