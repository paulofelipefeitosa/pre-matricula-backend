package br.edu.ufcg.computacao.psoft.prematriculabackend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
@CrossOrigin(value = "*")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @RequestMapping("/users")
    @ResponseBody
    public Object user(Authentication authentication) {
    	log.info(authentication.getPrincipal().toString());
        return authentication.getPrincipal();
    }
    
}
