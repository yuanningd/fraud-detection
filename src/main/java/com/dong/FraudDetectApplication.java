package com.dong;

import com.dong.model.Record;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FraudDetectApplication {
    public static void main(String[] args) throws IOException {
        List<String> recordLines = Files.readAllLines(Path.of("record.txt"));
        List<Record> records = new RecordParser().parse(recordLines);

       FraudulentDetector fraudulentDetector = new FraudulentDetector(records, 10);
       fraudulentDetector.getAllFraudulentHashedCreditNumbers().forEach(System.out::println);
    }
}
