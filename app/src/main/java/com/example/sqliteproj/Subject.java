package com.example.sqliteproj;

public class Subject {
    private String name;
    private String surname;
    private String subject;
    private int average;

    public Subject(String name, String surname, String subject, int average) {
        this.name = name;
        this.surname = surname;
        this.subject = subject;
        this.average = average;
    }

    public String getName() {
        return name;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
