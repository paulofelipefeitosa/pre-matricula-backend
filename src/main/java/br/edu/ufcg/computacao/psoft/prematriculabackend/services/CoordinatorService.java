package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Coordinator;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.CoordinatorProperties;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.Role;
import br.edu.ufcg.computacao.psoft.prematriculabackend.repositories.CoordinatorRepository;

@Service
public class CoordinatorService {

    @Autowired
    private CoordinatorRepository coordinatorRepository;

    @Autowired
    private CoordinatorProperties coordinatorProperties;

    @PostConstruct
    public void addCoordinators() {
        List<Coordinator> coordinatorsList = this.coordinatorProperties.getCoordinatorsList();
        for (Coordinator coordinator : coordinatorsList) {
            save(coordinator);
        }
    }

    public Coordinator save(Coordinator coordinator) {
        coordinator.setRole(Role.COORDINATOR);
        return this.coordinatorRepository.save(coordinator);
    }

    public Coordinator getCoordinatorByEnrollmentNumber(String enrollmentNumber) {
        return this.coordinatorRepository.findCoordinatorFromEnrollmentNumber(enrollmentNumber);
    }

    public Coordinator getCoordinatorByEmail(String email) {
        return this.coordinatorRepository.findCoordinatorFromEmail(email);
    }

    public boolean isCoordinator(String email) {
        boolean result = false;
        Coordinator coord = getCoordinatorByEmail(email);
        if (coord != null) {
            result = true;
        }
        return result;
    }
}
