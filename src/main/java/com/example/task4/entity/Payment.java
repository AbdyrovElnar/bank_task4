package com.example.task4.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Data
@RequiredArgsConstructor
public class Payment {

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


}
