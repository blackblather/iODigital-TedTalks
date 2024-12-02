package com.iodigital.tedtalks.service;

import com.iodigital.tedtalks.entity.Author;
import com.iodigital.tedtalks.entity.Talks;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Random;

@Service
public class ScraperService {

    // Services
    private AuthorService authorService;
    private TedTalksService tedTalksService;

    private CSVReaderHeaderAware reader;

    public ScraperService(AuthorService authorService,
                          TedTalksService tedTalksService) throws IOException {
        this.authorService = authorService;
        this.tedTalksService = tedTalksService;

        // Open CSV file
        InputStreamReader stream = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("data.csv"));
        reader = new CSVReaderHeaderAware(stream);
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
                Talks talk = new Talks(
                        author,
                        values.get("title"),
                        Integer.valueOf(values.get("views")),
                        Integer.valueOf(values.get("likes")),
                        values.get("link"),
                        values.get("date")
                );

                // Save to persistent storage
                authorService.createIfNotExists(author);
                if (tedTalksService.save(talk, author)) {
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
