package com.example.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class SecurityController {
//	@RequestMapping("/login")
//	public String login() {
//		return "login";
//	}
	@RequestMapping("/")
    public String defaultAction() {
    	return "redirect:/index";
    }
	@RequestMapping("/403")
    public String accesdenied() {
    	return "403";
    }
}
