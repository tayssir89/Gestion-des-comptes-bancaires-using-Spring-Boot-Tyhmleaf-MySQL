package com.example.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.controllers.dto.UserRegistrationDto;
import com.example.entities.User;
import com.example.metier.UserService;
@Controller
@RequestMapping("/registration")

public class UserRegisterController {
@Autowired
private UserService userService;
@ModelAttribute("user")
public UserRegistrationDto userRegistrationDto() {
    return new UserRegistrationDto();
}

@GetMapping
public String showRegistrationForm(Model model) {
    return "home/registration";
}

@PostMapping
public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                  BindingResult result){

    User existing = userService.findByEmail(userDto.getEmail());
    if (existing != null){
        result.rejectValue("email", null, "There is already an account registered with that email");
    }

    if (result.hasErrors()){
        return "home/registration";
    }

    userService.save(userDto);
    return "redirect:/registration?success";
}
}
