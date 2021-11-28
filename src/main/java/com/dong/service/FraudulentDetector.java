package com.dong.service;

import com.dong.calculator.FraudulentCalculator;
import com.dong.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

@Setter
@RequiredArgsConstructor
public class FraudulentDetector {
    private final FraudulentCalculator fraudulentCalculator;

    public List<String> getAllFraudulentHashedCreditNumbers(List<Transaction> transactions) {
        List<String> fraudulentHashedCreditNumbers = new ArrayList<>();
        HashMap<String, Queue<Transaction>> transactionInfoMap = covertTransactionInfoIntoMap(transactions);
        transactionInfoMap.forEach((hashedCreditNumber, transactionQueue) -> {
            if(fraudulentCalculator.calculateIfFraudulent(transactionQueue)) {
                fraudulentHashedCreditNumbers.add(hashedCreditNumber);
            };
        });
       return fraudulentHashedCreditNumbers;
    }

    private HashMap<String, Queue<Transaction>> covertTransactionInfoIntoMap(List<Transaction> transactions) {
        HashMap<String, Queue<Transaction>> transactionInfoMap = new HashMap<>();
        transactions.forEach(transaction -> {
            Queue<Transaction> transactionQueue= transactionInfoMap.getOrDefault(transaction.getHashedCreditNumber(), new ArrayDeque<>());
            transactionQueue.offer(transaction);
            transactionInfoMap.put(transaction.getHashedCreditNumber(), transactionQueue);
        });
        return transactionInfoMap;
    }

}
