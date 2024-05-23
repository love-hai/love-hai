FROM openjdk:17-jdk
# 将本地的 Jar 文件添加到容器中
COPY target/SpringDubbo-demo-comsumer-1.0-SNAPSHOT.jar /app/dubbo/SpringDubbo-demo-comsumer-1.0-SNAPSHOT.jar
# 指定容器启动时要执行的命令
CMD ["java", "-jar", "/app/dubbo/SpringDubbo-demo-comsumer-1.0-SNAPSHOT.jar"]

EXPOSE 8383