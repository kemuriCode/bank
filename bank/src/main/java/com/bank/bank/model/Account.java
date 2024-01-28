package com.bank.bank.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 11)
    private String pesel;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    // Metody walidujące
    @PrePersist
    @PreUpdate
    private void validate() {
        validateBalance();
        validateCurrency();
        validatePesel();
    }

    private void validateBalance() {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo konta musi być dodatnie");
        }
    }

    private void validateCurrency() {
        List<String> supportedCurrencies = new ArrayList<>(List.of("PLN", "EUR", "USD"));
        if (!supportedCurrencies.contains(currency)) {
            throw new IllegalArgumentException("Nieobsługiwana waluta: " + currency);
        }
    }


    private void validatePesel() {
        if (pesel == null || pesel.length() != 11) {
            throw new IllegalArgumentException("PESEL musi składać się z 11 cyfr");
        }
    }

}
