package ru.vk.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vk.testtask.model.Role;
import ru.vk.testtask.model.User;
import ru.vk.testtask.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void register(String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UsernameAlreadyExists(email);
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.ROLE_POSTS);

        userRepository.save(user);
    }

    public String getAuthenticatedUserName() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return null;
        }
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}
