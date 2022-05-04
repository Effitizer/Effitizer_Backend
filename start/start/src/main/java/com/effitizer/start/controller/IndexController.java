package com.effitizer.start.controller;

import com.effitizer.start.config.auth.dto.SessionUser;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class IndexController {
    @Autowired private HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){
        log.info("--- home controller - / -----------------------------------------");
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "login_test";
    }
}
