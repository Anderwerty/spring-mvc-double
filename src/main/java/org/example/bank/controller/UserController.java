package org.example.bank.controller;

import lombok.AllArgsConstructor;
import org.example.bank.dto.UserRegistrationDto;
import org.example.bank.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/registration")
    public String getRegistrationForm(ModelMap modelMap) {
        return "registration_form";
    }

    @PutMapping("/registration")
    public String register(UserRegistrationDto user, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.put("exceptionMessage", "");
            return "registration_form";
        }
        userService.register(user);
        modelMap.clear();

        return "redirect:/login";
    }
}
