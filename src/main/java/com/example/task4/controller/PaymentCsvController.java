package com.example.task4.controller;

import com.example.task4.dto.PaymentCsvDTO;
import com.example.task4.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentCsvController {
    private final PaymentService paymentService;

    @PostMapping(value = "/Csv")
    public ResponseEntity<List<PaymentCsvDTO>> convertXmlToJson(@RequestParam("fileSelect") MultipartFile fileSelect) throws IOException {

        List<PaymentCsvDTO> dtoList = paymentService.readCsvFile(fileSelect);


        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}
