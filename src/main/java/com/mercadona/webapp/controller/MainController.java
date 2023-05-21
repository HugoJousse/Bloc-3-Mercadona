package com.mercadona.webapp.controller;
import com.mercadona.webapp.model.User;
import com.mercadona.webapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class MainController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/log")
    public String login(){
        if(!userService.isUsersRegistered()){
            return "redirect:/register";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){

        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/registering")
    public String saveUser(User user, Model model, BindingResult result) {
        model.addAttribute("errors", false);
        if(userService.isPseudoExist(user.getPseudo())){
            model.addAttribute("samePseudo", "Ce pseudo est déjà utilisé!");
            return "register";
        }
        userService.saveUser(user);
        return "succes";
    }

    @GetMapping("/succes")
    public String succes() { return "succes"; }
}
