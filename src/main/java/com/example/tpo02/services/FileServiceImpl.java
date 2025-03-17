package com.example.tpo02.services;

import com.example.tpo02.entities.Entry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "pl.edu.pja.tpo02")
public class FileServiceImpl implements FileService {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void printSeparator() {
        System.out.println("----------------------------------------------");
    }

    @Override
    public List<Entry> parseCSVFile() {
        List<Entry> parsedEntries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // skip labels row
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Entry newEntry = new Entry(values[0], values[1], values[2]);
                parsedEntries.add(newEntry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsedEntries;
    }

    @Override
    public void saveToFile(Entry entry) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            String line = entry.getEnName() + "," + entry.getPlName() + "," + entry.getDeName() + "\n";
            writer.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
