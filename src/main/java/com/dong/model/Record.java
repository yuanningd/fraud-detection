package com.dong.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Record {
    private final String hashedCreditNumber;
    private final LocalDateTime timeStamp;
    private final double amount;
}
