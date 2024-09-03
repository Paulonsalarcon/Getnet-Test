package com.automacaoapi.pojo;

public class UserWithoutName {
    private String job;


    public UserWithoutName(String job) {
        this.job = job;
    }

    public Void getName() {
        return null;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}

