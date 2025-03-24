package com.example.tpo02.controllers;

import com.example.tpo02.entities.Entry;
import com.example.tpo02.services.FileServiceImpl;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class FlashcardsController {
    private final FileServiceImpl fileServiceImpl;
    private final Scanner scanner;

    public FlashcardsController(FileServiceImpl fileServiceImpl,
                                Scanner scanner) {
        this.fileServiceImpl = fileServiceImpl;
        this.scanner = scanner;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                     ------------------------------------------
                     |1| Add word
                     |2| Display words
                     |3| Test
                     |4| Exit
                     |?| Make a choice:
                     ------------------------------------------
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
                    runTest();
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
        if (english.isEmpty() || polish.isEmpty() || german.isEmpty()) {
            System.out.println("|ERROR| Do not type an empty string");
            return;
        }
        fileServiceImpl.addWordEntry(english, polish, german);
    }

    private void displayWordEntries() {
        fileServiceImpl.displayWordEntries();
    }

    private void runTest() {
        if (fileServiceImpl.isRepositoryEmpty()) {
            System.out.println("|INFO| No words are present in repository.");
            return;
        }

        Entry randomEntry = fileServiceImpl.getRandomEntry();
        System.out.println("|?| Translate the following word into Polish and German:");
        System.out.println("English: " + randomEntry.getEnName());

        System.out.print("Polish: ");
        String polishInput = scanner.nextLine();

        System.out.print("German: ");
        String germanInput = scanner.nextLine();

        fileServiceImpl.runTest(polishInput, germanInput, randomEntry);
    }
}
