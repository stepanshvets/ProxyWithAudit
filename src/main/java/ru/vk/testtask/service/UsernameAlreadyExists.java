package ru.vk.testtask.service;

public class UsernameAlreadyExists extends RuntimeException {
    public UsernameAlreadyExists(String username) {
        super("Username " + username + " already exists");
    }
}
