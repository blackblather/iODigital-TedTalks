package com.iodigital.tedtalks.service;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

@Service
public class FileService {

    public void loadFile() {
        try {
            // Open CSV file
            InputStreamReader reader = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("data.csv"));
            Map<String, String> values = new CSVReaderHeaderAware(reader).readMap();

            System.out.println("TODO");
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
