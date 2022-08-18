package com.example.task4.service;

import com.example.task4.dto.PaymentDTO;
import com.example.task4.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    public List<PaymentDTO> readFile(MultipartFile file) throws IOException {
        Workbook workbook = null;
        Sheet sheet = null;
        List<PaymentDTO> paymentList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        FileInputStream inputStream = new FileInputStream(new File(file.getOriginalFilename()));
        String extString = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if (".xlsx".equals(extString)) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (".xls".equals(extString)) {
            workbook = new HSSFWorkbook(inputStream);
        }
        sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Payment payment = new Payment();
            Row row = rowIterator.next();
            if (row.getRowNum() > 0) {
                Iterator<Cell> cellIterator = row.cellIterator();
                int index = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellType();
                    switch (index) {
                        case 0:
                            payment.setDate(LocalDateTime.parse(checkFormat(cell, cellType), formatter));
                            break;
                        case 1:
                            payment.setTerminal(checkFormat(cell, cellType));
                            break;
                        case 2:
                            payment.setAgent(checkFormat(cell, cellType));
                            break;
                        case 3:
                            payment.setChannel(checkFormat(cell, cellType));
                            break;
                        case 4:
                            payment.setService(checkFormat(cell, cellType));
                            break;
                        case 5:
                            payment.setProps(checkFormat(cell, cellType));
                            break;
                        case 6:
                            if (cell.getNumericCellValue()>0) {
                                payment.setSum(Integer.parseInt(checkFormat(cell, cellType)));
                                break;
                            }
                        case 7:
                            if (cell.getNumericCellValue()>0) {
                                payment.setAgentCommision(Integer.parseInt(checkFormat(cell, cellType)));
                                break;
                            }

                        case 8:
                            if (cell.getNumericCellValue()>0) {
                                payment.setCompanyCommision(Integer.parseInt(checkFormat(cell, cellType)));
                                break;
                            }
                        case 9:
                            payment.setStatus(checkFormat(cell, cellType));
                            break;
                    }
                    index++;
                }
                paymentList.add(PaymentDTO.from(payment));
            }

        }


        return paymentList;
    }


    public String checkFormat(Cell cell, CellType cellType) {
        switch (cellType) {
            case NUMERIC:
                long num = (long) cell.getNumericCellValue();
                return String.valueOf(num);
            case STRING:
                return cell.getStringCellValue().toString();
            case _NONE:
                break;
            case BLANK:
                break;
            case ERROR:
                break;
            case BOOLEAN:
                break;
            case FORMULA:
                break;
            default:
                break;
        }
        return "";
    }
}
