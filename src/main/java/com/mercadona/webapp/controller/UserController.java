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

    /**
     * Show the login form
     * @param model
     * @return String template : login form
     */
    @GetMapping("/log")
    public String login(Model model){
        if(!userService.isUsersRegistered()){
            model.addAttribute("user", new User());
            return "register";
        }
        return "login";
    }

    /**
     * logout the actual connected user
     * @return String tempate : index
     */
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

    /**
     * get the template of edit user form
     * redirect to accountOptions if the user is not the same as the one specified in URL
     * @param model
     * @param id
     * @return String : redirect to accountOptions or redirect to edit user template form
     */
    @GetMapping("/account/edit/{id}")
    public String accountEdit(Model model, @ModelAttribute("id") long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByPseudo(authentication.getName());
        User user = userService.getUserById(id);
        if(currentUser.getPseudo() == user.getPseudo()) {
            String pseudo = currentUser.getPseudo();
            model.addAttribute("user", currentUser);
            model.addAttribute("pseudo", pseudo);
            return "accountEdit";
        }
        return "redirect:/accountOptions";
    }

    /**
     * Edit connected user account
     * @param id
     * @param model
     * @param user
     * @param pseudo
     * @return String : redirect to index template
     */
    @PostMapping("/account/edit/{id}")
    public String setAccount(@PathVariable("id") long id, Model model,
                             @ModelAttribute("user") User user,
                             @RequestParam("pseudo") String pseudo) {
        User currentUser = userService.getUserById(id);
        model.addAttribute("errors", false);
        if(userService.isPseudoExist(user.getPseudo())){
            if(currentUser.getPseudo().equals(pseudo)) {
                userService.editUser(user);
                return "redirect:/";
            }
            model.addAttribute("samePseudo", "Ce pseudo est déjà utilisé!");
            return "accountEdit";
        }
        userService.editUser(user);
        return "redirect:/";
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
