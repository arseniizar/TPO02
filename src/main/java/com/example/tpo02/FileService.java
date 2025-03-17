package com.example.tpo02;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@ConfigurationProperties(prefix = "pl.edu.pja.tpo02")
public class FileService {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void parseCSVFile() {
        String filePath = "words.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println("English: " + values[0] + " | Polish: " + values[1] + " | German: " + values[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
