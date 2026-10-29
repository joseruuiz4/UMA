package es.tesaw.movies.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AutenticaController {


    @GetMapping("/")
    public String doInit(){
        return "login";
    }

    @PostMapping("/autentica")
    public String doAutentica(@RequestParam(value = "user", required = false) String user,
                              @RequestParam(value = "password", required = false) String password,
                              HttpSession session, Model model){




        return "";
    }

    @GetMapping("/salir")
    public String doSalir(HttpSession session){
        session.invalidate();
        return "redirect:/";

    }

}
