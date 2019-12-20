package com.arvent.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("arvent")
public class WebController {

    @GetMapping(value = "/hello")
    public String hello(
            @RequestHeader(value="Accept") String accept,
            @RequestHeader(value="Accept-Language") String acceptLanguage,
            @RequestHeader(value="User-Agent", defaultValue="foo") String userAgent,
            HttpServletResponse response) {

        System.out.println("accept: " + accept);
        System.out.println("acceptLanguage: " + acceptLanguage);
        System.out.println("userAgent: " + userAgent);

        return "hello";
    }

    @GetMapping("/index")
    public String home(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        return "index";
    }

}
