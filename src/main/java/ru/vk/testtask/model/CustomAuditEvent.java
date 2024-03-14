package ru.vk.testtask.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AuditEvent")
@Data
@NoArgsConstructor
public class CustomAuditEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date timestamp;

    private String type;

    private String URL;

    private String parameters;

    private String ip;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public CustomAuditEvent(Date timestamp, String type, String URL, String parameters, String ip, User user) {
        this.timestamp = timestamp;
        this.type = type;
        this.URL = URL;
        this.parameters = parameters;
        this.ip = ip;
        this.user = user;
    }
}
