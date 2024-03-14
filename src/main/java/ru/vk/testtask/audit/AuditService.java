package ru.vk.testtask.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vk.testtask.model.CustomAuditEvent;
import ru.vk.testtask.repository.AuditEventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditEventRepository auditEventRepository;

    @Transactional(readOnly = true)
    public List<CustomAuditEvent> getAll() {
        return auditEventRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<CustomAuditEvent> getByUserId(Integer userId) {
        return auditEventRepository.findByUserId(userId);
    }

    @Transactional
    public void save(CustomAuditEvent customAuditEvent) {
        auditEventRepository.save(customAuditEvent);
    }
}
