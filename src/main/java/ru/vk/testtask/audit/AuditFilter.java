package ru.vk.testtask.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.vk.testtask.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuditFilter extends OncePerRequestFilter {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        applicationEventPublisher.publishEvent(
                new HttpRequestAuditEvent(
                        userService.getAuthenticatedUserName(),
                        "access",
                        request
                )
        );

        filterChain.doFilter(request, response);
    }
}
