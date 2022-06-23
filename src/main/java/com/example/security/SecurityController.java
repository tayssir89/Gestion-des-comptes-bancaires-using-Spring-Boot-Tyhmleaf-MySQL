package com.example.security;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SecurityController {
	@RequestMapping(value="/login")
	public String login(Model model){
		return "home/login";
	}
	
	
	
}
