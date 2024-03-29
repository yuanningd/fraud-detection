package com.dong.service;

import com.dong.calculator.FraudulentCalculator;
import com.dong.model.Transaction;
import lombok.RequiredArgsConstructor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

@RequiredArgsConstructor
public class FraudulentDetector {
    private final FraudulentCalculator fraudulentCalculator;

    public List<String> getAllFraudulentHashedCreditNumbers(List<Transaction> transactions) {
        List<String> fraudulentHashedCreditNumbers = new ArrayList<>();
        Map<String, Queue<Transaction>> hashedCreditNumberAndTransactionsMap = groupTransactionsByHashedCreditNumber(transactions);
        hashedCreditNumberAndTransactionsMap.forEach((hashedCreditNumber, transactionQueue) -> {
            if (fraudulentCalculator.calculateIfFraudulent(transactionQueue)) {
                fraudulentHashedCreditNumbers.add(hashedCreditNumber);
            }
        });
        return fraudulentHashedCreditNumbers;
    }

    private Map<String, Queue<Transaction>> groupTransactionsByHashedCreditNumber(List<Transaction> transactions) {
        Map<String, Queue<Transaction>> hashedCreditNumberAndTransactionsMap = new HashMap<>();
        transactions.forEach(transaction -> {
            Queue<Transaction> transactionQueue = hashedCreditNumberAndTransactionsMap.getOrDefault(transaction.getHashedCreditNumber(), new ArrayDeque<>());
            transactionQueue.offer(transaction);
            hashedCreditNumberAndTransactionsMap.put(transaction.getHashedCreditNumber(), transactionQueue);
        });
        return hashedCreditNumberAndTransactionsMap;
    }

}

