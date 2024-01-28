package com.bank.bank.controller;

import com.bank.bank.model.Account;
import com.bank.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts") // Podstawowa ścieżka dla wszystkich endpointów związanych z kontami
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account newAccount = accountService.createAccount(account);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        Account account = accountService.getAccount(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/balance-greater-than/{balance}")
    public ResponseEntity<List<Account>> getAccountsByBalanceGreaterThan(@PathVariable BigDecimal balance) {
        List<Account> accounts = accountService.getAccountsByBalanceGreaterThan(balance);
        return ResponseEntity.ok(accounts);
    }
}
