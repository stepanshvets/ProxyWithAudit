package ru.vk.testtask.model;

public enum Permission {
    VIEWER_POSTS(Constants.VIEWER_POSTS_VALUE), EDITOR_POSTS(Constants.EDITOR_POSTS_VALUE),
    VIEWER_USERS(Constants.VIEWER_USERS_VALUE), EDITOR_USERS(Constants.EDITOR_USERS_VALUE),
    VIEWER_ALBUMS(Constants.VIEWER_ALBUMS_VAL), EDITOR_ALBUMS(Constants.EDITOR_ALBUMS_VALUE);


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public static class Constants {
        public static final String VIEWER_POSTS_VALUE = "VIEWER_POSTS";
        public static final String EDITOR_POSTS_VALUE = "EDITOR_POSTS";
        public static final String VIEWER_USERS_VALUE = "VIEWER_USERS";
        public static final String EDITOR_USERS_VALUE = "EDITOR_USERS";
        public static final String VIEWER_ALBUMS_VAL = "VIEWER_ALBUMS";
        public static final String EDITOR_ALBUMS_VALUE = "EDITOR_ALBUMS";
    }
}
