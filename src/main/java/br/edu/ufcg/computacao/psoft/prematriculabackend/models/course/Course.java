package br.edu.ufcg.computacao.psoft.prematriculabackend.models.course;

public class Course {
    private String name;
    private String code;
    private Integer credits;
    private Integer classLoad;
    private Type type;
    private PPC ppc;

    public Course(String name, String code, Integer credits, Integer classLoad, Type type,
            PPC ppc) {
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.classLoad = classLoad;
        this.type = type;
        this.ppc = ppc;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getCredits() {
        return credits;
    }

    public Integer getClassLoad() {
        return classLoad;
    }

    public Type getType() {
        return type;
    }

    public PPC getPcc() {
        return ppc;
    }

}
