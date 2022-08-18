package com.example.task4.controller;

import com.example.task4.dto.PaymentDTO;
import com.example.task4.entity.Payment;
import com.example.task4.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping(value = "/")
    public ResponseEntity<List<PaymentDTO>> convertXmlToJson(@RequestParam("fileSelect") MultipartFile fileSelect) throws IOException {

        List<PaymentDTO> dtoList = paymentService.readFile(fileSelect);


        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

}
