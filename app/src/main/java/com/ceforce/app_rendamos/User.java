package com.ceforce.app_rendamos;

public class User {
    int uid;
    String name;
    String givenName;
    String role;
    String access_token;
    public  User(int uid,String givenName,String role,String access_token){
        this.uid=uid;
        this.givenName=givenName;
        this.role=role;
        this.access_token=access_token;

    }
}
