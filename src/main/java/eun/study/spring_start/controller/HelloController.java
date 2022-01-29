package eun.study.spring_start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello( Model model) {
        model.addAttribute("data", "eunjung!!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-templete";

    }

    @GetMapping("hello-api-string")
    @ResponseBody   // 응답 body에 contentx를 직접 넣는다.
    public String helloApi(@RequestParam("name") String name, Model model) {
        return "hello " + name;    // StringHttpMessageConverter 동작
    }

    @GetMapping("hello-api-json")
    @ResponseBody
    public Hello HelloJson(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // 객체반환 -> key-value 방식의 json으로 반환 (JsonConverter 동작)
    }

    static class Hello {
        private String name;

        // 자동 getter/setter (Generate) => 단축키 : alt + insert
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
