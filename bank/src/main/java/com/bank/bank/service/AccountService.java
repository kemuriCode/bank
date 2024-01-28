package com.bank.bank.service;

import com.bank.bank.model.Account;
import com.bank.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        // Możesz tutaj dodać logikę biznesową przed stworzeniem konta, jeśli potrzebna
        return accountRepository.save(account);
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nie znaleziono konta z id: " + id));
    }

    public List<Account> getAccountsByBalanceGreaterThan(BigDecimal balance) {
        return accountRepository.findByBalanceGreaterThan(balance);
    }
}
