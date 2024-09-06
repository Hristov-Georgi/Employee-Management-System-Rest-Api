package com.sirmaacademy.employeemanagementsystemrestapi.service.impl;

import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Account;
import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.AccountDetails;
import com.sirmaacademy.employeemanagementsystemrestapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = this.accountRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Wrong username: " + username));

        return new AccountDetails(account);
    }

}
