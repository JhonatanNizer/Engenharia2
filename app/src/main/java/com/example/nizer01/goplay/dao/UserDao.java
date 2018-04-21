package com.example.nizer01.goplay.dao;

import com.example.nizer01.goplay.domain.User;

import java.util.ArrayList;

public class UserDao {

    private static ArrayList<User> userList = new ArrayList<>();

    public static void saveUser(User user){ userList.add(user); }

    public static User loadUser(){
        User user = new User();
        return user;
    }

    public static ArrayList<User> loadUserList() { return userList; }
}
