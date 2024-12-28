package Actors;

import Models.Person;

import java.util.Date;

public class Account {
    private int id;
    private String password;
    private String status;
    private Person person;

    public Account(int id, String password, String status, Person person) {
        this.id = id;
        this.password = password;
        this.status = status;
        this.person = person;
    }
    public void resetPassword(String password){
        this.password=password;
        System.out.println("Password Reset Successfully!!");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
