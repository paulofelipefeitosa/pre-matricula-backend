package br.edu.ufcg.computacao.psoft.prematriculabackend.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = UserController.USERS_ENDPOINT)
public class UserController {

    public static final String USERS_ENDPOINT = "/users";

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Object user(Authentication authentication) {
        return authentication.getPrincipal();
    }

}
