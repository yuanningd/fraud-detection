package com.dong;

import com.dong.model.Transaction;
import com.dong.service.FraudulentCalculator;
import com.dong.service.FraudulentDetector;
import com.dong.service.TransactionParser;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FraudDetectApplication {
    public static void main(String[] args) throws IOException {
        List<String> recordLines = Files.readAllLines(Path.of("record.txt"));
        List<Transaction> transactions = new TransactionParser().parse(recordLines);

       FraudulentDetector fraudulentDetector = new FraudulentDetector(new FraudulentCalculator(new BigDecimal("10")));
       fraudulentDetector.getAllFraudulentHashedCreditNumbers(transactions).forEach(System.out::println);
    }
}
