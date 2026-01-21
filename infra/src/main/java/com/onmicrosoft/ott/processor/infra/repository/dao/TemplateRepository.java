package com.onmicrosoft.ott.processor.infra.repository.dao;

import com.onmicrosoft.ott.processor.core.domain.common.PageData;
import com.onmicrosoft.ott.processor.infra.repository.entity.TemplateEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TemplateRepository  extends JpaRepository<TemplateEntity, Long> {
    @Query("""
    SELECT t FROM TemplateEntity t
    WHERE (:eventType IS NULL OR t.eventType = :eventType)
      AND (:languageCode IS NULL OR t.languageCode = :languageCode)
    ORDER BY t.id ASC
""")
    List<TemplateEntity> findFirstPage(
            @Param("eventType") String eventType,
            @Param("languageCode") String languageCode,
            Pageable pageable
    );
    @Query("""
    SELECT t FROM TemplateEntity t
    WHERE t.id > :cursor
      AND (:eventType IS NULL OR t.eventType = :eventType)
      AND (:languageCode IS NULL OR t.languageCode = :languageCode)
    ORDER BY t.id ASC
""")
    List<TemplateEntity> findNextPage(
            @Param("cursor") Long cursor,
            @Param("eventType") String eventType,
            @Param("languageCode") String languageCode,
            Pageable pageable
    );


}
