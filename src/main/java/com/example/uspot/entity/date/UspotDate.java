package com.example.uspot.entity.date;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
@Setter
public class UspotDate {

    // 등록일자
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regisDate;

    // 수정일자
    @LastModifiedDate
    private LocalDateTime updateDate;

}
