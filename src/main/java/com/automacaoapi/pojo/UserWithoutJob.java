package com.automacaoapi.pojo;

public class UserWithoutJob {
    private String name;
    private String job;


    public UserWithoutJob(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Void getJob() {
        return null;
    }
}