package com.example.tpo02.repositories.entry;

import com.example.tpo02.entities.Entry;

import java.util.List;
import java.util.Optional;

public interface EntryRepository {
    Entry addEntry(Entry entry);
    List<Entry> getAllEntries();
    Optional<Entry> getEntryById(long id);
    void deleteEntryById(long id);
    void deleteEntry(Entry entry);
}
