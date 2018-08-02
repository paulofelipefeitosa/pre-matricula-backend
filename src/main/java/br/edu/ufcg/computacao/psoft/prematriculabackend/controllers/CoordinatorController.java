package br.edu.ufcg.computacao.psoft.prematriculabackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.UnauthorizedException;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Coordinator;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Role;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.AuthenticationService;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.CoordinatorService;
import br.edu.ufcg.computacao.psoft.prematriculabackend.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/coordinators")
public class CoordinatorController {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private CoordinatorService coordService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Coordinator getStudent(@RequestHeader(required = true,
            value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
        String email = this.authService.getEmail(tokenValue);
        Coordinator coord = this.coordService.getCoordinatorByEmail(email);
        if (coord != null) {
            return coord;
        }
        throw new UnauthorizedException("Unauthorized operation");
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Coordinator createUser(@RequestBody Coordinator coordinator,
            @RequestHeader(required = true,
                    value = AuthenticationController.TOKEN_VALUE_HEADER_KEY) String tokenValue) {
        String email = this.authService.getEmail(tokenValue);
        User user = this.userService.getUserByEmail(email);
        if (user == null || user.getRole().equals(Role.COORDINATOR)) {
            if (email.equals(coordinator.getEmail())) {
                return this.coordService.save(coordinator);
            }
        }
        throw new UnauthorizedException("Unauthorized operation");
    }
}
