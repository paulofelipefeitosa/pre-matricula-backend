package br.edu.ufcg.computacao.psoft.prematriculabackend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UnauthenticatedUserController {
  
    @RequestMapping("/unauthenticated")
    public String unauthenticated() {
        return "redirect:/?error=true";
    }
    
}
