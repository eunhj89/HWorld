package com.uno.hworld.domain;

import com.uno.hworld.common.UserAuth;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "USER")
public class User extends SystemColumnEntity implements UserDetails {

    @Id
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "USER_PW", nullable = false)
    private String userPw;

    @Column(name = "USER_NM", nullable = false)
    private String userNm;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_AUTH", nullable = false)
    private UserAuth userAuth;

    @Column(name = "IS_LOCK", nullable = false)
    private Boolean isLock = false;

    @Column(name = "LOGIN_FAIL_COUNT", nullable = false)
    private int loginFailCount = 0;

    @OneToMany(mappedBy = "user")
    private List<FileInfo> fileInfos = new ArrayList<>();

    @Builder
    public User(String userId, String userPw, String userNm, UserAuth userAuth, Boolean isLock, int loginFailCount) {
        this.userId = userId;
        this.userPw = userPw;
        this.userNm = userNm;
        this.userAuth = userAuth;
        this.isLock = Optional.ofNullable(isLock).orElse(false);
        this.loginFailCount = loginFailCount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.userAuth.getValue()));
    }

    @Override
    public String getPassword() {
        return this.userPw;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.getDelYn();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isLock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.getDelYn() && !this.isLock;
    }
}
