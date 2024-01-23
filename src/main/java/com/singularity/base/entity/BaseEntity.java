package com.singularity.base.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Comment("생성 일시")
    private LocalDateTime createdDateTime;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    @Comment("생성 사용자")
    private String createUserId;

    @LastModifiedDate
    @Column(nullable = false)
    @Comment("수정 일시")
    private LocalDateTime lastModifiedDateTime;

    @LastModifiedBy
    @Column(nullable = false)
    @Comment("수정 사용자")
    private String lastModifiedUserId;

    @Version
    @Comment("버전")
    private int version;

//    public <DTO> DTO toDto(Class<DTO> resDtoClass) {
//        return BaseConst.OM.convertValue(this, resDtoClass);
//    }
}