package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.exceptions.UnauthorizedException;

@Service
public class AuthenticationService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CoordinatorService coordService;

    private Map<String, String> tokenMapper = new HashMap<String, String>();

    public String createToken(String token, String email) {
        if (isAuthorizedEmail(email)) {
            this.tokenMapper.put(token, email);
            return token;
        }
        throw new UnauthorizedException("User does not belong to CCC organization");
    }

    private boolean isAuthorizedEmail(String email) {
        return this.studentService.isStudentEmail(email) || this.coordService.isCoordinator(email);
    }

    public String getEmail(String token) {
        if (this.tokenMapper.containsKey(token)) {
            return this.tokenMapper.get(token);
        }
        throw new UnauthorizedException("User not authenticated");
    }

}
