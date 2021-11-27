package com.dong;

import com.dong.model.Record;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FraudulentDetector {
    private final List<Record> records;
    private final double threshold;

    public List<String> getAllFraudulentHashedCreditNumbers() {
        Set<LocalDate> dates = records.stream()
                .map(record -> record.getTimeStamp().toLocalDate())
                .collect(Collectors.toSet());
        return dates.stream()
                .flatMap(date -> getFraudulentHashedCreditNumbersByDate(date).stream())
                .collect(Collectors.toList());
    }

    private boolean detectIfFraudulentByHashedCreditNumberAndDate(String hashedCreditNumber, LocalDate date) {
        return threshold < records.stream()
                .filter(record -> record.getHashedCreditNumber().equals(hashedCreditNumber))
                .filter(record -> record.getTimeStamp().toLocalDate().equals(date))
                .mapToDouble(Record::getAmount)
                .sum();
    }

    private List<String> getFraudulentHashedCreditNumbersByDate(LocalDate date) {
        Set<String> hashedCreditNumbers = records.stream()
                .map(Record::getHashedCreditNumber)
                .collect(Collectors.toSet());
        return hashedCreditNumbers.stream()
                .filter(hashedCreditNumber -> detectIfFraudulentByHashedCreditNumberAndDate(hashedCreditNumber, date))
                .collect(Collectors.toList());
    }

}
