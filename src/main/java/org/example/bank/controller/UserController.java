package org.example.bank.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.bank.dto.UserDto;
import org.example.bank.dto.UserRegistrationDto;
import org.example.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("newUser", new UserRegistrationDto());

        return "registration_form";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("user") @Valid UserRegistrationDto user,
                           BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("exceptionMessage", "");
            return "registration_form";
        }

        userService.register(user);

        return "redirect:/login";
    }


    // GET  /users?page=1&perPage=10
    // GET  /users?page=blabla&perPage=
    @GetMapping("/users")
    public String getAllUsers(@RequestParam(defaultValue = "1", name = "page") String page,
                              @RequestParam(defaultValue = "10") String perPage,
                              ModelMap model) {
        Integer userPage = convert(page, 1);
        Integer userPerPage = convert(perPage, 10);

        Page<UserDto> users = userService.findAll(userPage, userPerPage);
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("users/pdf/{filename}")
    public @ResponseBody byte[] downloadPdf(@PathVariable("filename") String filename) {
        return userService.loadUsersAsPdf(filename);
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("text", "Our team works on this issue");

//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("error/error_page");
//        modelAndView.addObject("");
        System.out.println(ex);
        return "error/error_page";
    }


    private static Integer convert(String number, Integer defaultValue) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
