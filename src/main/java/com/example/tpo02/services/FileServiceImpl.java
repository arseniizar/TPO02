package com.example.tpo02.services;

import com.example.tpo02.config.FileExternalProperties;
import com.example.tpo02.entities.Entry;
import com.example.tpo02.profiles.IWordFormatter;
import com.example.tpo02.repositories.entry.EntryRepositoryImpl;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FileServiceImpl implements FileService {
    private final FileExternalProperties fileExternalProperties;
    private final EntryRepositoryImpl entryRepository;
    private final IWordFormatter wordFormatter;

    public FileServiceImpl(EntryRepositoryImpl entryRepository, IWordFormatter wordFormatter, FileExternalProperties fileExternalProperties) {
        this.entryRepository = entryRepository;
        this.wordFormatter = wordFormatter;
        this.fileExternalProperties = fileExternalProperties;
    }

    @Override
    public void printSeparator() {
        System.out.println("----------------------------------------------");
    }

    @Override
    public void runTest(String polishAnswer, String germanAnswer, Entry entry) {
        boolean correctPolish = polishAnswer.equalsIgnoreCase(entry.getPlName());
        boolean correctGerman = germanAnswer.equalsIgnoreCase(entry.getDeName());
        if (correctPolish && correctGerman) {
            System.out.println("|!| Correct!");
        } else {
            System.out.println("|!| Incorrect. The correct answers are:");
            System.out.println("Polish: " + entry.getPlName());
            System.out.println("German: " + entry.getDeName());
        }
    }

    @Override
    public void addWordEntry(String english, String polish, String german) {
        Entry entry = new Entry(english, polish, german);
        entryRepository.addEntry(entry);
        saveToFile(entry);
        System.out.println("|INFO| Word added successfully!");
    }

    @Override
    public void displayWordEntries() {
        List<Entry> entries = entryRepository.getAllEntries();
        // each string is 3 words in respective languages
        List<String> formattedEntries = wordFormatter.formatWords(entries);
        for (String formattedEntry : formattedEntries) {
            System.out.println(formattedEntry);
        }
    }

    @Override
    public void addParsedEntriesToRepository() {
        List<Entry> parsedEntries = parseCSVFile();
        for (Entry entry : parsedEntries) {
            entryRepository.addEntry(entry);
        }
    }

    @Override
    public boolean isRepositoryEmpty() {
        List<Entry> entries = entryRepository.getAllEntries();
        return entries.isEmpty();
    }

    @Override
    public Entry getRandomEntry() {
        List<Entry> entries = entryRepository.getAllEntries();
        Random random = new Random();
        return entries.get(random.nextInt(entries.size()));
    }

    @Override
    public List<Entry> parseCSVFile() {
        List<Entry> parsedEntries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileExternalProperties.getFileName()))) {
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
        try (FileWriter writer = new FileWriter(fileExternalProperties.getFileName(), true)) {
            String line = entry.getEnName() + "," + entry.getPlName() + "," + entry.getDeName() + "\n";
            writer.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
