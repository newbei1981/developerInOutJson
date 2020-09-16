package com.newbei.developerInfoInOutJson.model;

public class Skill {
    private long id;
    private String name;

    public Skill() {}

    public Skill(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Skill: "  + id +") = "+
                "  " + name + '\'';
    }
}
