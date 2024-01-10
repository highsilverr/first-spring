package com.example.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model){

        model.addAttribute("username", "고은");
        return "greetings"; //templates/greeting.mustache -> 브라우저로 전송
    }
}
