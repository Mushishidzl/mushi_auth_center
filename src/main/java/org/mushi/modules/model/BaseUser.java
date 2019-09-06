package org.mushi.modules.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 *  用户信息
 **/
@Data
public class BaseUser implements UserDetails {

    private String id;

    private String username;

    private String email;

    private Boolean isEnable;

    private Boolean isExpired;

    private Boolean isLocked;

    private String password;

    private String gender;

    private List<GrantedAuthority> authorities;

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnable;
    }
}
