package com.example.task4.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class PaymentCsv {

    private String transactionId;

    private LocalDateTime date;

    private String userNumber;

    private double sum;
}
