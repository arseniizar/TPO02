package com.example.tpo02.profiles;

import com.example.tpo02.Entry;

import java.util.List;

public interface IWordFormatter {
    abstract List<String> formatWords(List<Entry> entries);
    abstract String formatWord(Entry entry);
}
