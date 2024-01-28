package com.bank.bank.service;

import com.bank.bank.model.Account;
import com.bank.bank.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccount_success() {
        Account account = new Account(null, "12345678901", new BigDecimal("100.00"), "PLN", "Jan", "Kowalski");
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        
        Account createdAccount = accountService.createAccount(account);
        
        assertNotNull(createdAccount);
        assertEquals("12345678901", createdAccount.getPesel());
    }
    
    @Test
    void createAccount_nullAccount_throwsException() {
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> accountService.createAccount(null)
        );
        
        assertEquals("Account cannot be null", thrown.getMessage());
    }

    @Test
    void getAccountById_found() {
        Long accountId = 1L;
        Account account = new Account(accountId, "12345678901", new BigDecimal("250.00"), "PLN", "Anna", "Nowak");
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        
        Account foundAccount = accountService.getAccount(accountId);
        
        assertNotNull(foundAccount);
        assertEquals(accountId, foundAccount.getId());
    }

    @Test
    void getAccountById_notFound_throwsException() {
        Long accountId = 2L;
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> accountService.getAccount(accountId)
        );
        
        assertEquals("Account not found", thrown.getMessage());
    }

    @Test
    void getAccountsByBalanceGreaterThan_found() {
        BigDecimal balance = new BigDecimal("100.00");
        Account account1 = new Account(1L, "12345678901", new BigDecimal("250.00"), "PLN", "Anna", "Nowak");
        Account account2 = new Account(2L, "12345678902", new BigDecimal("150.00"), "PLN", "Jan", "Kowalski");
        when(accountRepository.findByBalanceGreaterThan(balance)).thenReturn(List.of(account1, account2));
        
        List<Account> accounts = accountService.getAccountsByBalanceGreaterThan(balance);
        
        assertNotNull(accounts);
        assertEquals(2, accounts.size());
    }

}
