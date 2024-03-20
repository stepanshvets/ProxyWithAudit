package ru.vk.testtask.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vk.testtask.model.AuditEvent;
import ru.vk.testtask.repository.AuditEventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditEventRepository auditEventRepository;

    @Transactional(readOnly = true)
    public List<AuditEvent> getAll() {
        return auditEventRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<AuditEvent> getByUserId(Integer userId) {
        return auditEventRepository.findByUserId(userId);
    }

    @Transactional
    public void save(AuditEvent auditEvent) {
        auditEventRepository.save(auditEvent);
    }
}
