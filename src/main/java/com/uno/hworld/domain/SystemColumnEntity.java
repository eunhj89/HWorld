package com.uno.hworld.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class SystemColumnEntity {

    @CreatedDate
    @Column(name = "CREATE_DATE", nullable = false, updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "UPDATE_DATE", nullable = false, updatable = true)
    private Date updateDate;

    @Column(name = "DEL_YN", nullable = false, length = 1)
    private Boolean delYn = false;

}
