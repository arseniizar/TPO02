package com.example.tpo02.repositories.entry;

import com.example.tpo02.entities.Entry;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
@Transactional
public class EntryRepositoryImpl implements EntryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Entry addEntry(Entry entry) {
        if (entry.getId() == null) {
            entityManager.persist(entry);
            return entry;
        } else {
            return entityManager.merge(entry);
        }
    }

    @Override
    public List<Entry> getAllEntries() {
        return entityManager.
                createQuery("SELECT e FROM Entry e", Entry.class)
                .getResultList();
    }

    @Override
    public Optional<Entry> getEntryById(long id) {
        Entry entry = entityManager.find(Entry.class, id);
        return Optional.ofNullable(entry);
    }

    @Override
    public void deleteEntryById(long id) {
        getEntryById(id).ifPresent(
                entry -> entityManager.remove(entry));
    }

    @Override
    public void deleteEntry(Entry entry) {
        boolean isEntryExists = entityManager.contains(entry);
        if (isEntryExists) {
            entityManager.remove(entry);
        }
    }
}
