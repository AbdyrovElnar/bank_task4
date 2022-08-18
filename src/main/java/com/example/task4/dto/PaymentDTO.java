package com.example.task4.dto;

import com.example.task4.entity.Payment;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentDTO {

    private LocalDateTime date;

    private String terminal;

    private String agent;

    private String channel;
    private String service;
    private String props;

    private int sum;
    private int agentCommision;
    private int companyCommision;

    private String status;

    public static PaymentDTO from(Payment payment) {
        return builder()
                .date(payment.getDate())
                .terminal(payment.getTerminal())
                .agent(payment.getAgent())
                .channel(payment.getChannel())
                .service(payment.getService())
                .props(payment.getProps())
                .sum(payment.getSum())
                .agentCommision(payment.getAgentCommision())
                .companyCommision(payment.getCompanyCommision())
                .status(payment.getStatus())
                .build();
    }


}
