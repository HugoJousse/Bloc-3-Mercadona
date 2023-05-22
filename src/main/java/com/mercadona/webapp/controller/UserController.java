package com.mercadona.webapp.controller;
import com.mercadona.webapp.model.User;
import com.mercadona.webapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/log")
    public String login(Model model){
        if(!userService.isUsersRegistered()){
            return "redirect:/register";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){

        return "index";
    }

    /**
     * Get the register form
     * @param model
     * @return template register.html
     */
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * get the modification account form for the actual user
     * @param id
     * @param model
     * @return template accountOptions.html
     */
    @GetMapping("/accountOptions")
    public String account(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByPseudo(authentication.getName());
        model.addAttribute("user", user);
        return "accountOptions";
    }

    @GetMapping("/account/edit/{id}")
    public String accountEdit(Model model, @ModelAttribute("id") long id) {
        User user = userService.getUserById(id);
        String pseudo = user.getPseudo();
        model.addAttribute("user", user);
        model.addAttribute("pseudo", pseudo);
        return "accountEdit";
    }

    @PostMapping("/account/edit/{id}")
    public String setAccount(@PathVariable("id") long id, Model model,
                             @ModelAttribute("user") User user,
                             @ModelAttribute("pseudo") String pseudo) {
        model.addAttribute("errors", false);
        if(userService.isPseudoExist(user.getPseudo()) && !user.getPseudo().equals(pseudo)){
            model.addAttribute("samePseudo", "Ce pseudo est déjà utilisé!");
            return "accountEdit";
        }
        userService.editUser(user);
        return "index";
    }

    /**
     * post the register form
     * @param user
     * @param model
     * @param result
     * @return template login.html
     */
    @PostMapping("/registering")
    public String saveUser(Model model, @ModelAttribute("user") User user, BindingResult result) {
        model.addAttribute("errors", false);
        if(userService.isPseudoExist(user.getPseudo())){
            model.addAttribute("samePseudo", "Ce pseudo est déjà utilisé!");
            return "register";
        }
        userService.saveUser(user);
        return "login";
    }

}
