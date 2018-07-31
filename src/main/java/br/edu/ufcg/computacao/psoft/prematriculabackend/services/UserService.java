package br.edu.ufcg.computacao.psoft.prematriculabackend.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import br.edu.ufcg.computacao.psoft.prematriculabackend.models.user.User;

@Service
public class UserService {
    private List<User> userList = new ArrayList<User>();

    public User create(User user) {
        this.userList.add(user);
        return this.userList.get(this.userList.size() - 1);
    }

    public User getUserByEmail(String email) {
        for (User user : this.userList) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

}
