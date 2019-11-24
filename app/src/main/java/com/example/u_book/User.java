package com.example.u_book;

public class User {

    public   String username, name, lastname;
    public  int id;

    public User(int id, String username, String name, String lastname) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastname = lastname;

    }

    public User() {
        getid();
        getusername();
        getlastname();
        getname();
    }


    public  int getid(){


        return  id;
    }
    public String getusername(){

        return username;
    }
    public  String getname(){
        return  name;

    }

    public  String getlastname(){
        return lastname;
    }
}