package com.careminder.backend.global.auth;

import com.careminder.backend.model.account.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

@Getter
public class CustomUserDetails implements UserDetails {

    private final Long accountId;
    private final Collection<GrantedAuthority> roles;

    public CustomUserDetails(final Long accountId, final Collection<GrantedAuthority> roles) {
        this.accountId = accountId;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {

        return null;
    }

    @Override
    public String getUsername() {

        return null;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    public Role getRole(){
        String roleString = Objects.requireNonNull(roles.stream().findFirst().orElse(null)).getAuthority();
        try{
            return Role.valueOf(roleString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + roleString, e);
        }
    }
}
