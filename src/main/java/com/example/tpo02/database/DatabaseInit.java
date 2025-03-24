package com.example.tpo02.database;

import com.example.tpo02.entities.Entry;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Component
public class DatabaseInit implements CommandLineRunner {
    private static final String path = "./src/main/resources/data/mydb.mv.db";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) {
        File file = new File(path);

        if (!file.exists()) {
            initAndPopulate();
        } else {
            List<Entry> initialEntries = List.of(
                    new Entry("Sunflower", "Słonecznik", "Sonnenblume"),
                    new Entry("Mountain", "Góra", "Berg"),
                    new Entry("River", "Rzeka", "Fluss"),
                    new Entry("Star", "Gwiazda", "Stern")
            );

            initialEntries.forEach(entityManager::persist);
        }
    }

    private void initAndPopulate() {

    }
}
