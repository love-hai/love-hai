package com.lovehai.helloSecurity.controller;

import com.lovehai.helloSecurity.entity.pm.OpenAiChatParam;
import com.lovehai.helloSecurity.entity.pm.OpenAiMessageParam;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.core.JsonField;
import com.openai.models.*;
import com.openai.models.chat.completions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author xiahaifeng
 */
@RestController
@RequestMapping("/zhipu")
@Slf4j
public class ZhiPuBigModelController {
        @PostMapping("/test")
        public static String testDate(@RequestBody OpenAiChatParam pm) {
            OpenAIClient client = OpenAIOkHttpClient.builder()
                    .baseUrl("https://open.bigmodel.cn/api/paas/v4/")
                    .apiKey("cbc2ac69b8c147f9a5df508590f99f97.7bsfhwRs3d3MTdvZ")
                    .build();
            ChatCompletionCreateParams.Builder paramsBuilder = ChatCompletionCreateParams.builder()
                    .model("glm-4");
            for (OpenAiMessageParam msg : pm.getMsgList()) {
                String role = msg.getRole();
                if ("assistant".equals(role)) {
                    ChatCompletionAssistantMessageParam assistantMessageParam = ChatCompletionAssistantMessageParam
                            .builder().name("assistant").content(msg.getContent()).build();
                    paramsBuilder.addMessage(assistantMessageParam);
                } else if ("user".equals(role)) {
                    ChatCompletionUserMessageParam userMessageParam = ChatCompletionUserMessageParam
                            .builder().name("user").content(msg.getContent()).build();
                    paramsBuilder.addMessage(userMessageParam);
                }
            }
            ChatCompletionCreateParams params = paramsBuilder.build();
            ChatCompletion chatCompletion = client.chat().completions().create(params);
            JsonField<ChatCompletionMessage> JsonMessage = chatCompletion.choices().get(0)._message();

            ChatCompletionMessage message = JsonMessage.accept(new JsonField.Visitor<>() {
                @Override
                public ChatCompletionMessage visitKnown(ChatCompletionMessage m) {
                    return m;
                }
            });
            String role = message._role().convert(String.class);
            return message.content().orElse(null);
        }
//    @PostMapping("/test")
//    public static String testDate(@RequestBody OpenAiChatParam pm) {
//        OpenAIClient client = OpenAIOkHttpClient.builder()
//                .baseUrl("https://open.bigmodel.cn/api/paas/v4/")
//                .apiKey("cbc2ac69b8c147f9a5df508590f99f97.7bsfhwRs3d3MTdvZ")
//                .build();
//        ChatCompletionCreateParams.Builder paramsBuilder = ChatCompletionCreateParams.builder()
//                .model("glm-4");
//        for (OpenAiMessageParam msg : pm.getMsgList()) {
//            String role = msg.getRole();
//            if ("assistant".equals(role)) {
//                ChatCompletionAssistantMessageParam assistantMessageParam = ChatCompletionAssistantMessageParam
//                        .builder().name("assistant").content(ChatCompletionAssistantMessageParam.Content.ofTextContent(msg.getContent())).build();
//                paramsBuilder.addMessage(ChatCompletionMessageParam.ofChatCompletionAssistantMessageParam(assistantMessageParam));
//            } else if ("user".equals(role)) {
//                ChatCompletionUserMessageParam userMessageParam = ChatCompletionUserMessageParam
//                        .builder().name("user").content(ChatCompletionUserMessageParam.Content.ofTextContent(msg.getContent())).build();
//                paramsBuilder.addMessage(ChatCompletionMessageParam.ofChatCompletionUserMessageParam(userMessageParam));
//            }
//        }
//        ChatCompletionCreateParams params = paramsBuilder.build();
//        ChatCompletion chatCompletion = client.chat().completions().create(params);
//        JsonField<ChatCompletionMessage> JsonMessage = chatCompletion.choices().get(0)._message();
//
//        ChatCompletionMessage message = JsonMessage.accept(new JsonField.Visitor<>() {
//            @Override
//            public ChatCompletionMessage visitKnown(ChatCompletionMessage m) {
//                return m;
//            }
//        });
////    String role = message._role().convert(String.class);
//        return message.content().orElse(null);
//    }
//
//    public static class Visitor implements com.openai.core.JsonField.Visitor<String, String> {
//        @Override
//        public String visitKnown(String T) {
//            return T;
//        }
//    }


    public static void main(String[] args) {
        OpenAiChatParam openAiChatParam = new OpenAiChatParam();
        List<OpenAiMessageParam> msgList = new ArrayList<>();
        openAiChatParam.setMsgList(msgList);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // 客户端 输入 问题
            System.out.print("你：");
            String input = scanner.nextLine().trim();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }
            OpenAiMessageParam param = new OpenAiMessageParam();
            param.setRole("user");
            param.setContent(input);
            msgList.add(param);
            String out = testDate(openAiChatParam);
            System.out.println(out);
            param = new OpenAiMessageParam();
            param.setRole("assistant");
            param.setContent(out);
            msgList.add(param);
        }
    }
}