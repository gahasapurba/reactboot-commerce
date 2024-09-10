package com.gahasa.ecommerce.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gahasa.ecommerce.entity.Pengguna;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserDetailsImpl implements UserDetails {

    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String nama;
    @JsonIgnore
    private String roles;

    public UserDetailsImpl(String username, String password, String email, String nama, String roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nama = nama;
        this.roles = roles;
    }

    public static UserDetailsImpl build(Pengguna pengguna) {
        return new UserDetailsImpl(pengguna.getId(), pengguna.getPassword(), pengguna.getEmail(), pengguna.getNama(), pengguna.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (StringUtils.hasText(roles)) {
            String[] splits = roles.replaceAll(" ", "").split(",");
            for (String string : splits) {
                authorities.add(new SimpleGrantedAuthority(string));
            }
        }
        return authorities;
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
}
