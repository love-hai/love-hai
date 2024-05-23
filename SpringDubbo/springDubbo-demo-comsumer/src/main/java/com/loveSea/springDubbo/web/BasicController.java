package com.loveSea.springDubbo.web;

import com.LoveSea.SpringDubbo.web.User;
import com.loveSea.springDubbo.api.DemoService;
import com.loveSea.springDubbo.model.vo.TbUserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class BasicController {
    @DubboReference
    private DemoService demoService;

    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    // http://127.0.0.1:8080/user
    @RequestMapping("/user")
    @ResponseBody
    public User user() {
        User user = new User();
        user.setName("theonefx");
        user.setAge(666);
        return user;
    }

    // http://127.0.0.1:8080/save_user?name=newName&age=11
    @GetMapping("/save")
    @ResponseBody
    public TbUserVo saveUser(@RequestBody User u) {
        try {
            TbUserVo tbUserVo = demoService.sayHello(u.getName(), u.getPassword());
            log.info("save user:{}", tbUserVo);
            return tbUserVo;
        } catch (Exception e) {
            log.error("save user error", e);
        }
        return null;
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html() {
        return "index.html";
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name, @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
        user.setName("zhangsan");
        user.setAge(18);
    }
}
