package org.isbel8ai.training.datasources.service;

import org.isbel8ai.training.datasources.model.account.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAccounts();
}
