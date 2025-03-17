package com.example.tpo02.repositories;

import com.example.tpo02.entities.Entry;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EntryRepository {
 private final List<Entry> entries = new ArrayList<>();

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    public List<Entry> getAllEntries() {
        return new ArrayList<>(entries);
    }
}
