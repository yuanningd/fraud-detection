package com.dong;

import com.dong.model.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FraudDetectApplication {
    public static void main(String[] args) throws IOException {
        List<String> recordLines = Files.readAllLines(Path.of("record.txt"));
        List<Transaction> transactions = new TransactionParser().parse(recordLines);

       FraudulentDetector fraudulentDetector = new FraudulentDetector();
       fraudulentDetector.getAllFraudulentHashedCreditNumbers(transactions, new BigDecimal("10")).forEach(System.out::println);
    }
}
