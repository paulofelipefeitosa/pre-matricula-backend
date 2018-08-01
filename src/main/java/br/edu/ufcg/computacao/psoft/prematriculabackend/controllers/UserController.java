package br.edu.ufcg.computacao.psoft.prematriculabackend.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(value = "*")
public class UserController {
	
    @RequestMapping("/users")
    @ResponseBody
    public Object user(Authentication authentication) {
        return authentication.getPrincipal();
    }
    
}
