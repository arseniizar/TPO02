package com.example.tpo02.profiles;

import com.example.tpo02.Entry;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Primary
@Component
@Profile("Default")
public class DefaultCaseFormatter implements IWordFormatter {

    @Override
    public List<String> formatWords(List<Entry> entries) {
        List<String> formatted = new ArrayList<>();
        formatted.add("||word|| en | pl | de");
        for (Entry entry : entries) {
            formatted.add(formatWord(entry));
        }
        return formatted;
    }

    @Override
    public String formatWord(Entry entry) {
        return "|" + entry.getEnName() + "| " + entry.getPlName() + " | " + entry.getDeName();
    }

}
