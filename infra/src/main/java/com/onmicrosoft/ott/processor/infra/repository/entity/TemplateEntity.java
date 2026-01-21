package com.onmicrosoft.ott.processor.infra.repository.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "template")
@Getter
@Setter
public class TemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(name = "channel_type", nullable = false)
    private String channelType; // Nếu là Enum ở DB, nên dùng String hoặc Enum kèm @Enumerated

    @Column(name = "title_template")
    private String titleTemplate;

    @Column(name = "message_template", nullable = false)
    private String messageTemplate;

    @Column(name = "language_code", nullable = false, length = 2)
    private String languageCode;

    @Column(name = "category")
    private String category;

    @Column(name = "is_push_only")
    private Boolean isPushOnly;

    @Column(name = "service_required")
    private String serviceRequired;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;
}
