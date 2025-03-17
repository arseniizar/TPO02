package com.example.tpo02;

import com.example.tpo02.profiles.IWordFormatter;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Controller
public class FlashcardsController {
    private final EntryRepository entryRepository;
    private final FileService fileService;
    private final IWordFormatter wordFormatter;
    private final Scanner scanner;

    public FlashcardsController(EntryRepository entryRepository, FileService fileService,
                                IWordFormatter wordFormatter, Scanner scanner) {
        this.entryRepository = entryRepository;
        this.fileService = fileService;
        this.wordFormatter = wordFormatter;
        this.scanner = scanner;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                     |1| Add word
                     |2| Display words
                     |3| Test
                     |4| Exit
                     |?| Make a choice:
                    """);
            int chosenOption = scanner.nextInt();
            scanner.nextLine();
            switch (chosenOption) {
                case 1:
                    addWordEntry();
                    break;
                case 2:
                    displayWordEntries();
                    break;
                case 3:
                    testUser();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("|ERROR| Invalid choice. Try again.");
            }
        }
    }

    private String scanEntry(String lan) {
        System.out.print("|?| Enter word in " + lan + ": ");
        return scanner.nextLine();
    }

    private void addWordEntry() {
        String english = scanEntry("English");
        String polish = scanEntry("Polish");
        String german = scanEntry("German");
        Entry entry = new Entry(english, polish, german);
        entryRepository.addEntry(entry);
        System.out.println("|INFO| Word added successfully!");
    }

    private void displayWordEntries() {
        List<Entry> entries = entryRepository.getAllEntries();
        // each string is 3 words in respective languages
        List<String> formattedEntries = wordFormatter.formatWords(entries);
        for (String formattedEntry : formattedEntries) {
            System.out.println(formattedEntry);
        }
    }

    private void testUser() {
        List<Entry> entries = entryRepository.getAllEntries();
        if (entries.isEmpty()) {
            System.out.println("No words available to test.");
            return;
        }
        Random random = new Random();
        Entry entry = entries.get(random.nextInt(entries.size()));
        System.out.println("Translate the following word into Polish and German:");
        System.out.println("Word: " + entry.getEnName());
        System.out.print("Polish: ");
        String polishAnswer = scanner.nextLine().trim();
        System.out.print("German: ");
        String germanAnswer = scanner.nextLine().trim();
        boolean correctPolish = polishAnswer.equalsIgnoreCase(entry.getPlName());
        boolean correctGerman = germanAnswer.equalsIgnoreCase(entry.getDeName());
        if (correctPolish && correctGerman) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answers are:");
            System.out.println("Polish: " + entry.getPlName());
            System.out.println("German: " + entry.getDeName());
        }
    }
}
