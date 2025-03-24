package com.example.tpo02.profiles;

import com.example.tpo02.entities.Entry;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("Upper")
public class UpperCaseFormatter implements IWordFormatter {

    @Override
    public List<String> formatWords(List<Entry> entries) {
        List<String> formatted = new ArrayList<>();
        formatted.add("en | pl | de".toUpperCase());
        for (Entry entry : entries) {
            formatted.add(formatWord(entry));
        }
        return formatted;
    }

    @Override
    public String formatWord(Entry entry) {
        return (entry.getEnName() + "| " + entry.getPlName() + " | " + entry.getDeName()).toUpperCase();
    }
}
