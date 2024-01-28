package com.bank.bank.service;

import com.bank.bank.model.Account;
import com.bank.bank.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.List;

public class AccountService {
    
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Konto nie może być null");
        }
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Konto o id " + id + " nie istnieje"));
    }
    
    public List<Account> getAccountsByBalanceGreaterThan(BigDecimal balance) {
        return accountRepository.findByBalanceGreaterThan(balance);
    }
}
