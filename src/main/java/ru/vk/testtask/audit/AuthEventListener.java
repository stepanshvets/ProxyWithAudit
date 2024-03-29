package ru.vk.testtask.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import ru.vk.testtask.model.AuditEvent;
import ru.vk.testtask.model.User;
import ru.vk.testtask.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class AuthEventListener {
    private final AuditService auditService;
    private final UserService userService;

    // todo: change for different impl-s AuthorizationFailureEvent
    // todo: add correct ip
    @EventListener
    public void onAuthFailureEvent(AuthorizationFailureEvent event) {
        FilterInvocation filterInvocation = (FilterInvocation) event.getSource();
        HttpServletRequest request = filterInvocation.getRequest();

        User user = null;
        if (!event.getAuthentication().getPrincipal().equals("anonymousUser")) {
            user = userService.findByEmail(((UserDetails) event.getAuthentication()
                    .getPrincipal()).getUsername());
        }

        // only for basic auth
        String type = (request.getRequestURL().toString().split("/")[1].contains("error")) ? "login error" : "no access";

        AuditEvent auditEvent = new AuditEvent(new Date(),
                type,
                request.getRequestURL().toString(),
                request.getMethod(),
                request.getQueryString(),
                request.getHeader("X-FORWARDED-FOR"),
                user);

        auditService.save(auditEvent);
    }

    @EventListener
    public void onAuthSuccessEvent(AuthenticationSuccessEvent event) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) event.getSource();

        User user = userService.findByEmail(((UserDetails) event.getAuthentication()
                .getPrincipal()).getUsername());

        AuditEvent auditEvent = new AuditEvent(new Date(),
                "login success",
                null,
                null,
                null,
                null,
                user);

        auditService.save(auditEvent);
    }

    @EventListener
    public void onHttpRequestEvent(HttpRequestAuditEvent event) {
        User user = userService.findByEmail(event.getPrincipal());

        AuditEvent auditEvent = new AuditEvent(new Date(),
                event.getType(),
                event.getRequest().getRequestURL().toString(),
                event.getRequest().getMethod(),
                event.getRequest().getQueryString(),
                event.getRequest().getHeader("X-FORWARDED-FOR"),
                user);

        auditService.save(auditEvent);
    }
}
