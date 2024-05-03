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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("newUser", new UserRegistrationDto());

        return "registration_form";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("newUser") @Valid UserRegistrationDto user,
                           BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            model.addAttribute("exceptionMessage", message);
            return "registration_form";
        }

        userService.register(user);

        return "redirect:/login";
    }


    // GET  /users?page=1&perPage=10
    // GET  /users?page=blabla&perPage=
    @GetMapping("/users")
    public String getAllUsers(@RequestParam(defaultValue = "1", name = "page", required = false) String page,
                              @RequestParam(defaultValue = "10", name = "perPage", required = false) String perPage,
                              Model model) {
        Integer userPage = convert(page, 1);
        Integer userPerPage = convert(perPage, 10);

        Page<UserDto> usersPage = userService.findAll(userPage - 1, userPerPage);
        model.addAttribute("users", usersPage.getContent());
        model.addAttribute("numberOfPages", usersPage.getTotalPages());

        return "users";
    }

    @GetMapping("users/pdf/{filename}")
    public @ResponseBody byte[] downloadPdf(@PathVariable("filename") String filename) {
        return userService.loadUsersAsPdf(filename);
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("text", "Our team works on this issue");
        model.addAttribute("exception", ex.toString());
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("cause", ex.getCause());

//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("error/error_page");
//        modelAndView.addObject("");
        System.out.println(ex);
        return "error/error_page";
    }


    private static Integer convert(String number, Integer defaultValue) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException | NullPointerException e) {
            return defaultValue;
        }
    }
}
