package org.isbel8ai.training.datasources.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isbel8ai.training.datasources.model.account.Account;
import org.isbel8ai.training.datasources.repository.account.AccountRepository;
import org.isbel8ai.training.datasources.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        Account account = Account.builder().build();
        accountRepository.save(account);
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}