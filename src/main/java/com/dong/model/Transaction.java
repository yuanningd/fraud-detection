package com.dong.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Transaction {
    private final String hashedCreditNumber;
    private final LocalDateTime timeStamp;
    private final BigDecimal amount;
}
