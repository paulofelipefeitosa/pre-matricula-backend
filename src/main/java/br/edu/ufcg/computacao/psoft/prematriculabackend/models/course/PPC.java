package br.edu.ufcg.computacao.psoft.prematriculabackend.models.course;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PPC {
    NOVO("Novo"), ANTIGO("Antigo"), AMBOS("Ambos");

    private String value;

    PPC() {}
    
    private PPC(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
