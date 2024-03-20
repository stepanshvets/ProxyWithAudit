package ru.vk.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vk.testtask.model.AuditEvent;

import java.util.List;

@Repository
public interface AuditEventRepository extends JpaRepository<AuditEvent, Long> {
    @Query("select c from AuditEvent c where c.user.id = ?1")
    List<AuditEvent> findByUserId(Integer userId);

}
