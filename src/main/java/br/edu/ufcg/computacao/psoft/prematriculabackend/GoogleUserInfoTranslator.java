package br.edu.ufcg.computacao.psoft.prematriculabackend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class GoogleUserInfoTranslator implements UserInfoTranslator {

    @Override
    public String getEmail(Map<String, Object> map) {
        ArrayList<HashMap<String, String>> emailProperty =
                (ArrayList<HashMap<String, String>>) map.get("emails");
        HashMap<String, String> emailValue = emailProperty.get(0);
        String email = emailValue.get("value");
        return email;
    }

    @Override
    public String getName(Map<String, Object> map) {
        return map.get("displayName").toString();
    }

}
