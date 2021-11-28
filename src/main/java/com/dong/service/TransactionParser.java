package com.dong.service;

import com.dong.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransactionParser {
    private static final String SEPARATOR = ",";
    public List<Transaction> parse(List<String> lines) {
        return lines.stream().map(this::parseTransaction).collect(Collectors.toList());
    }

    private Transaction parseTransaction(String line) {
        if(Objects.isNull(line) || line.trim().length() == 0) {
            return null;
        }
        String[] recordAttrs = Arrays.stream(line.split(SEPARATOR)).map(String::trim).toArray(String[]::new);
        if(recordAttrs.length != 3) {
            throw new IllegalArgumentException("Item format is not correct: " + line);
        }
        return new Transaction(recordAttrs[0], LocalDateTime.parse(recordAttrs[1]), new BigDecimal(recordAttrs[2]));
    }

}
