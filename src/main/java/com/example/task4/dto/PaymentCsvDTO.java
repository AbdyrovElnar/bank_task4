package com.example.task4.dto;

import com.example.task4.entity.Payment;
import com.example.task4.entity.PaymentCsv;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class PaymentCsvDTO {
    private String transactionId;

    private LocalDateTime date;

    private String userNumber;

    private double sum;

    public static PaymentCsvDTO from(PaymentCsv payment) {
        return builder()
                .transactionId(payment.getTransactionId())
                .date(payment.getDate())
                .userNumber(payment.getUserNumber())
                .sum(payment.getSum())
                .build();
    }

}
