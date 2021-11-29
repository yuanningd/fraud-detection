package com.dong.calculator;

import com.dong.model.Transaction;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

@RequiredArgsConstructor
public class FraudulentCalculator {
    private final BigDecimal threshold;

    public boolean calculateIfFraudulent(Queue<Transaction> transactionQueue) {
        Deque<Transaction> slidingWindow = new ArrayDeque<>();
        while(!transactionQueue.isEmpty()){
            slidingWindow.offer(transactionQueue.poll());
            while (slidingWindow.getLast().getTimeStamp().isAfter(slidingWindow.getFirst().getTimeStamp().plusDays(1))
                    && slidingWindow.size() > 1) {
                slidingWindow.poll();
            }
            if(slidingWindow.stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add).compareTo(threshold) > 0) {
                return true;
            }
        }
        return false;
    }
}
