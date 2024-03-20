package ru.vk.testtask.audit;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestAuditEvent extends ApplicationEvent {
    private final String principal;
    private final String type;
    private final HttpServletRequest request;

    public HttpRequestAuditEvent(String principal, String type, HttpServletRequest request) {
        super(new Object());
        this.principal = principal;
        this.type = type;
        this.request = request;
    }

    public String getPrincipal() {
        return principal;
    }

    public String getType() {
        return type;
    }

    public HttpServletRequest getRequest() {
        return request;
    }
}
