package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ModelAndView showForm() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("user", new User());
        return model;
    }

    @PostMapping("/validateUser")
    public ModelAndView checkValidity(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) {
       new User().validate(user,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView model = new ModelAndView("index");
            return model;
        }
        ModelAndView model = new ModelAndView("result");
        userService.save(user);
        return model;
    }
}
