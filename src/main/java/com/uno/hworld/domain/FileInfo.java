package com.uno.hworld.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "FILE_INFO")
public class FileInfo extends SystemColumnEntity{

    @Id
    @Column(name="FILE_ID", nullable = false)
    private String fileId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(name = "FILE_TYPE", nullable = false)
    private String fileType;

    @Column(name = "FILE_ORG_NM", nullable = false)
    private String fileOrgNm;

    @Column(name = "FILE_SAVED_NM", nullable = false)
    private String fileSavedNm;

    @Column(name = "HIDE_YN", nullable = false)
    private Boolean hideYn = false;

    @Column(name = "IS_LOCK", nullable = false)
    private Boolean isLock = false;

    @Builder
    public FileInfo(String fileId, User user, String fileType, String fileOrgNm, String fileSavedNm, Boolean hideYn, Boolean isLock) {
        this.fileId = fileId;
        this.user = user;
        this.fileType = fileType;
        this.fileOrgNm = fileOrgNm;
        this.fileSavedNm = fileSavedNm;
        this.hideYn = Optional.ofNullable(hideYn).orElse(false);
        this.isLock = Optional.ofNullable(isLock).orElse(false);
    }

}
