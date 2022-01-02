package com.example.restfulpractice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/1/1
 * @since
 */
@RestController
public class RestfulPractiseController {
    @GetMapping(value = "/testRest")
    public String testRest() {
        return "hello world";
    }
}
