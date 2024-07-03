package com.LoveSea.fengCore.retryable;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

/**
 * @author xiahaifeng
 */
@Mojo(name = "retry-compile", defaultPhase = LifecyclePhase.PROCESS_CLASSES)
public class RetryCompileTaskMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        // 读取 META-INF/retryMethods.xml 文件 成RetryMethod 列表
        try {
            // 获取编译输出目录
            String outputDirectoryPath = project.getBuild().getOutputDirectory();
            String packaging = project.getPackaging();
            File outputDirectory = new File(outputDirectoryPath);
            File xmlFile = new File(outputDirectory, "META-INF/retryMethods.xml");

            if (xmlFile.exists()) {
                // 创建 JAXB 上下文和 Unmarshaller
                JAXBContext context = JAXBContext.newInstance(RetryMethodsWrapper.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();

                // 读取并反序列化 XML 文件
                RetryMethodsWrapper wrapper = (RetryMethodsWrapper) unmarshaller.unmarshal(xmlFile);
                List<RetryMethod> retryMethods = wrapper.getRetryMethods();
//                RetryMethodModifier retryMethodModifier = new RetryMethodModifier(outputDirectoryPath,project);
//                retryMethodModifier.modifyMethod(retryMethods);
                RetryMethodAsmModifier retryMethodAsmModifier = new RetryMethodAsmModifier(outputDirectoryPath);
                retryMethodAsmModifier.modifyMethod(retryMethods);
            } else {
                getLog().warn("File META-INF/retryMethods.xml does not exist.");
            }
        } catch (Exception e) {
            throw new MojoExecutionException("Error ", e);
        }
    }
}
