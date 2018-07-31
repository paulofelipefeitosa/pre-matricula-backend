package br.edu.ufcg.computacao.psoft.prematriculabackend;

import java.util.Map;

public interface UserInfoTranslator {
    
    public String getEmail(Map<String, Object> map);
    
    public String getName(Map<String, Object> map);

}
