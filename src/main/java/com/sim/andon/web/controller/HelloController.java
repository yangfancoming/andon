package com.sim.andon.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by 64274 on 2019/10/7.
 *
 * @ Description: TODO
 * @ author  山羊来了
 * @ date 2019/10/7---19:15
 */
@Controller
public class HelloController {

    // 测试地址： http://localhost:8099/test1
    @GetMapping("/test1")
    public String hellola(){
        return "1";
    }

}
