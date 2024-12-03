package com.iodigital.tedtalks.service;

import com.iodigital.tedtalks.entity.Author;
import com.iodigital.tedtalks.entity.Talk;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

@Service
public class ScraperService {

    // Services
    private AuthorService authorService;
    private TedTalksService tedTalksService;

    private CSVReaderHeaderAware reader;
    private DateTimeFormatter formatter;

    public ScraperService(AuthorService authorService,
                          TedTalksService tedTalksService) throws IOException {
        this.authorService = authorService;
        this.tedTalksService = tedTalksService;

        // Open CSV file
        InputStreamReader stream = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("data.csv"));
        reader = new CSVReaderHeaderAware(stream);

        // Config date formater
        this.formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
    }

    public int refresh() {
        try {
            // Init zeroed success count
            int newTalksFound = 0;

            // Simulate that we found some talks from the web
            int maxCount = new Random().nextInt(0, 10);
            Map<String, String> values;
            for (int i = 0; i < maxCount && (values = reader.readMap()) != null; i++) {
                // Map CSV row to Author
                Author author = new Author(
                        values.get("author")
                );

                // Map CSV row to Talk
                Talk talk = new Talk(
                        author,
                        values.get("title"),
                        Integer.parseInt(values.get("views")),
                        Integer.parseInt(values.get("likes")),
                        values.get("link"),
                        YearMonth.parse(values.get("date"), formatter).getYear()
                );

                // Save to persistent storage
                authorService.createIfNotExists(author);
                if (tedTalksService.createIfNotExists(talk)) {
                    newTalksFound++;
                }
            }

            // Return nr of talks successfully retrieved
            System.out.println("Found " + newTalksFound + " TedTalks...");
            return newTalksFound;
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
