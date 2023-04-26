package com.dong;

import com.dong.calculator.FraudulentCalculator;
import com.dong.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FraudulentCalculatorTest {
    private FraudulentCalculator fraudulentCalculator;
    private Queue<Transaction> testQueue1;
    private Queue<Transaction> testQueue2;

    @BeforeEach
    public void setUp() {
        Transaction t1 = new Transaction("1", LocalDateTime.parse("2021-11-26T17:15:54"), BigDecimal.valueOf(2));
        Transaction t2 = new Transaction("1", LocalDateTime.parse("2021-11-26T16:15:54"), BigDecimal.valueOf(5));
        Transaction t3 = new Transaction("1", LocalDateTime.parse("2021-11-27T10:15:54"), BigDecimal.valueOf(4));
        Transaction t4 = new Transaction("1", LocalDateTime.parse("2021-11-29T16:15:54"), BigDecimal.valueOf(8));

        testQueue1 = new ArrayDeque<>();
        testQueue2 = new ArrayDeque<>();

        testQueue1.offer(t1);
        testQueue1.offer(t2);
        testQueue1.offer(t3);

        testQueue2.offer(t2);
        testQueue2.offer(t3);
        testQueue2.offer(t4);

        fraudulentCalculator = new FraudulentCalculator(BigDecimal.valueOf(10));
    }

    @Test
    void shouldReturnTrueGivenTestQueue1() {
        boolean ifFraudulent = fraudulentCalculator.calculateIfFraudulent(testQueue1);
        assertTrue(ifFraudulent);
    }

    @Test
    void shouldReturnFalseGivenTestQueue2() {
        boolean ifFraudulent = fraudulentCalculator.calculateIfFraudulent(testQueue2);
        assertFalse(ifFraudulent);
    }

}
