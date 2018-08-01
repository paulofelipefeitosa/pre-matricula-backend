package br.edu.ufcg.computacao.psoft.prematriculabackend.models.course;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Type {
    OBRIGATORIA("Obrigatoria"), OPTATIVA_GERAL("Optativa-Geral"), OPTATIVA_ESPECIFICA(
            "Optativa-Especifica");

    private String value;

    Type() {}
    
    private Type(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
