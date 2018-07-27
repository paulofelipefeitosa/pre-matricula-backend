package br.edu.ufcg.computacao.psoft.prematriculabackend.controllers;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/users")
    @ResponseBody
    public Principal user(Principal principal) {
        return principal;
    }
    
}
