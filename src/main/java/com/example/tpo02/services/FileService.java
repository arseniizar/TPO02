package com.example.tpo02.services;

import com.example.tpo02.entities.Entry;

import java.util.List;

public interface FileService {
    abstract List<Entry> parseCSVFile();

    abstract void saveToFile(Entry entry);

    abstract void printSeparator();

    abstract void runTest(String polishAnswer, String germanAnswer, Entry entry);

    abstract void addWordEntry(String english, String polish, String german);

    abstract void displayWordEntries();

    abstract void addParsedEntriesToRepository();

    abstract boolean isRepositoryEmpty();

    abstract Entry getRandomEntry();
}
