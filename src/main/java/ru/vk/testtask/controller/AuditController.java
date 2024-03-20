package ru.vk.testtask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vk.testtask.audit.AuditService;
import ru.vk.testtask.model.AuditEvent;

import java.util.List;

@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditController {
    private final AuditService auditService;

    @GetMapping()
    public List<AuditEvent> getByUserId(@RequestParam Integer userId) {
        return auditService.getByUserId(userId);
    }
}
