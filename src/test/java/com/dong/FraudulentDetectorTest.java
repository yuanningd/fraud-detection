package com.dong;

import com.dong.calculator.FraudulentCalculator;
import com.dong.model.Transaction;
import com.dong.service.FraudulentDetector;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class FraudulentDetectorTest {

    @Test
    public void shouldReturnFraudulentHashedCreditNumbers() {
        Transaction t1 = new Transaction("1", LocalDateTime.parse("2021-11-26T17:15:54"), BigDecimal.valueOf(10));
        Transaction t2 = new Transaction("2", LocalDateTime.parse("2021-11-26T16:15:54"), BigDecimal.valueOf(10));
        List<Transaction> transactions = List.of(t1, t2);

        FraudulentCalculator fraudulentCalculator = mock(FraudulentCalculator.class);
        when(fraudulentCalculator.calculateIfFraudulent(any())).thenReturn(true, false);
        FraudulentDetector fraudulentDetector = new FraudulentDetector(fraudulentCalculator);

        List<String> fraudulentHashedCreditNumbers = fraudulentDetector.getAllFraudulentHashedCreditNumbers(transactions);
        assertEquals(List.of("1"), fraudulentHashedCreditNumbers);
    }

}
