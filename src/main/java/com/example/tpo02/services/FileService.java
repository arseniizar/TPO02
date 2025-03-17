package com.example.tpo02.services;

import com.example.tpo02.entities.Entry;

import java.util.List;

public interface FileService {
    abstract List<Entry> parseCSVFile();

    abstract void saveToFile(Entry entry);

    abstract void printSeparator();
}
