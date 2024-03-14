package ru.vk.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vk.testtask.model.CustomAuditEvent;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuditEventRepository extends JpaRepository<CustomAuditEvent, Long> {
    @Query("select c from CustomAuditEvent c where c.user.id = ?1")
    List<CustomAuditEvent> findByUserId(Integer userId);

}
