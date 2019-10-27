package com.ceforce.app_rendamos.user;

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

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getRole() {
        return role;
    }

    public String getAccess_token() {
        return access_token;
    }
}
