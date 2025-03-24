package com.sirmaacademy.employeemanagementsystemrestapi.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDetails implements UserDetails {

    private final String username;
    private final String password;
    private final List<GrantedAuthority> grantedAuthorities;

    public AccountDetails(Account account) {
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.grantedAuthorities = account
                .getEmployee()
                .getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(grantedAuthorities);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

}
