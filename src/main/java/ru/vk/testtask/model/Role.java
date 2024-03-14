package ru.vk.testtask.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ROLE_ADMIN(Set.of(Permission.VIEWER_POSTS, Permission.EDITOR_POSTS,
            Permission.VIEWER_USERS, Permission.EDITOR_USERS,
            Permission.VIEWER_ALBUMS, Permission.EDITOR_ALBUMS)),
    ROLE_POSTS(Set.of(Permission.VIEWER_POSTS, Permission.EDITOR_POSTS)),
    ROLE_USERS(Set.of(Permission.VIEWER_USERS, Permission.EDITOR_USERS)),
    ROLE_ALBUMS(Set.of(Permission.VIEWER_ALBUMS, Permission.EDITOR_ALBUMS));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return this.permissions.stream().map(authority ->
                        new SimpleGrantedAuthority(authority.getPermission()))
                .collect(Collectors.toSet());
    }
}
