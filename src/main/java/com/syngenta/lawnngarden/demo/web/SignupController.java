package com.syngenta.lawnngarden.demo.web;

import javax.servlet.http.HttpSession;

import com.syngenta.lawnngarden.demo.po.User;
import com.syngenta.lawnngarden.demo.service.UserService;

import org.springframework.util.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private UserService userService = new UserService();

    @GetMapping()
    String signupPage(){
        return "signup";
    }

    @PostMapping()
    public String signup(User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("flag", false);
            model.addAttribute("message", "Error: Can not create account.");
            return "/admin/add-type";
        }

        User u = userService.findUser(user.getEmail());
        if (u != null){ // Find Duplicat Type
            redirectAttributes.addFlashAttribute("flag", false);
            redirectAttributes.addFlashAttribute("message", "The email has been signed up, please sign in!");
        }else{
            user.setPassWord(DigestUtils.md5DigestAsHex(user.getPassWord().getBytes()));
            u = userService.saveUser(user);
            if (u == null){
                redirectAttributes.addFlashAttribute("flag", false);
                redirectAttributes.addFlashAttribute("message", "Error: Fial to save user.");
            }
            else {
                redirectAttributes.addFlashAttribute("flag", true);
                redirectAttributes.addFlashAttribute("message", String.format("Congrad %s, your account has been created! Please login with your eamil and password.", u.getFirstName()));
            }
        }
        
        return "redirect:/login";
    }

        
}

