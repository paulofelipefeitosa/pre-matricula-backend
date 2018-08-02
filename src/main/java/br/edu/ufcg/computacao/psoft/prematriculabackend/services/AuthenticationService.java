package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.UnauthorizedException;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Role;

@Service
public class AuthenticationService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CoordinatorService coordService;

    private Map<String, String> tokenMapper = new HashMap<String, String>();

    public Role createToken(String token, String email) {
        if (this.coordService.isCoordinator(email)) {
            this.tokenMapper.put(token, email);
            return Role.COORDINATOR;
        } else if(this.studentService.isStudentEmail(email)) {
            this.tokenMapper.put(token, email);
            return Role.STUDENT;
        }
        throw new UnauthorizedException("User does not belong to CCC organization");
    }

    public String getEmail(String token) {
        if (this.tokenMapper.containsKey(token)) {
            return this.tokenMapper.get(token);
        }
        throw new UnauthorizedException("User not authenticated");
    }

}
