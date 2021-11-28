package com.dong;

import com.dong.model.Transaction;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
public class FraudulentDetector {
    private List<Transaction> transactions;
    private BigDecimal threshold;

    public List<String> getAllFraudulentHashedCreditNumbers(List<Transaction> transactions, BigDecimal threshold) {
        initialiseFraudulentDetector(transactions, threshold);
        Set<LocalDate> dates = this.transactions.stream()
                .map(transaction -> transaction.getTimeStamp().toLocalDate())
                .collect(Collectors.toSet());
        return dates.stream()
                .flatMap(date -> getFraudulentHashedCreditNumbersByDate(date).stream())
                .collect(Collectors.toList());
    }

    private void initialiseFraudulentDetector (List<Transaction> transactions, BigDecimal threshold) {
        setThreshold(threshold);
        setTransactions(transactions);
    }

    private boolean detectIfFraudulentByHashedCreditNumberAndDate(String hashedCreditNumber, LocalDate date) {
         BigDecimal totalAmount = transactions.stream()
                .filter(transaction -> transaction.getHashedCreditNumber().equals(hashedCreditNumber))
                .filter(transaction -> transaction.getTimeStamp().toLocalDate().equals(date))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalAmount.compareTo(threshold) > 0;
    }

    private List<String> getFraudulentHashedCreditNumbersByDate(LocalDate date) {
        Set<String> hashedCreditNumbers = transactions.stream()
                .map(Transaction::getHashedCreditNumber)
                .collect(Collectors.toSet());
        return hashedCreditNumbers.stream()
                .filter(hashedCreditNumber -> detectIfFraudulentByHashedCreditNumberAndDate(hashedCreditNumber, date))
                .collect(Collectors.toList());
    }

}
