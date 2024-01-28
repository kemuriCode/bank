package com.bank.bank.repository;

import com.bank.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Dodatkowa metoda do pobierania kont z saldem większym niż podana wartość
    List<Account> findByBalanceGreaterThan(BigDecimal balance);
}
