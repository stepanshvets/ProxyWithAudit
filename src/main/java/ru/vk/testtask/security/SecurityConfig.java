package ru.vk.testtask.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.vk.testtask.model.Permission;
import ru.vk.testtask.model.Role;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/test/bar").hasAuthority(Permission.VIEWER_POSTS.getPermission())
                .antMatchers("/test/foo").hasAuthority(Permission.EDITOR_POSTS.getPermission())

                //auth
                .antMatchers(HttpMethod.POST,"/auth/register").permitAll()

                //audit
                .antMatchers("/audit/**").permitAll()

                //posts
                .antMatchers(HttpMethod.GET,"/api/posts/**").hasAuthority(Permission.VIEWER_POSTS.getPermission())
                .antMatchers(HttpMethod.POST,"/api/posts/**").hasAuthority(Permission.EDITOR_POSTS.getPermission())

                //users
                .antMatchers(HttpMethod.GET,"/api/users/**").hasAuthority(Permission.VIEWER_USERS.getPermission())
                .antMatchers(HttpMethod.POST,"/api/users/**").hasAuthority(Permission.EDITOR_USERS.getPermission())

                //albums
                .antMatchers(HttpMethod.GET,"/api/albums/**").hasAuthority(Permission.VIEWER_ALBUMS.getPermission())
                .antMatchers(HttpMethod.POST,"/api/albums/**").hasAuthority(Permission.EDITOR_ALBUMS.getPermission())

//                .anyRequest()
//                .authenticated()
                .and()
                .authenticationManager(authenticationManager())
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        ProviderManager providerManager = new ProviderManager(List.of(authenticationProvider()));
        providerManager.setAuthenticationEventPublisher(authenticationEventPublisher());
        return providerManager;
    }

    @Bean
    public DefaultAuthenticationEventPublisher authenticationEventPublisher() {
        return new DefaultAuthenticationEventPublisher();
    }

    @Bean
    public AuthenticationProvider authenticationProviderInMemory() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .authorities(Role.ROLE_ADMIN.getAuthorities())
                        .build(),
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("user"))
                        .authorities(Role.ROLE_POSTS.getAuthorities())
                        .build()
        ));
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
