package com.syngenta.lawnngarden.demo.web;

import javax.servlet.http.HttpSession;

import com.syngenta.lawnngarden.demo.po.User;
import com.syngenta.lawnngarden.demo.service.UserService;

import org.springframework.util.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String loginPage(){
        return "login";
    }

    @PostMapping()
    public String login(@RequestParam String email, 
        @RequestParam String passWord, 
        HttpSession httpSession,
        RedirectAttributes redirectAttributes){
        System.out.println(email);  
        System.out.println(DigestUtils.md5DigestAsHex(passWord.getBytes()));
        User user = userService.checkUser(email, DigestUtils.md5DigestAsHex(passWord.getBytes()));
        if (user != null){
            user.setPassWord(null); // Dont put password to session
            httpSession.setAttribute("user", user);
            return "/index";
        }
        else{
            redirectAttributes.addFlashAttribute("message", "Username or Password Invilid"); // remind user
            return "redirect:/login"; // Redirect Page
        }
        
    }

    @GetMapping("/logout")
    public String logout (HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "redirect:/login";

    }
}
