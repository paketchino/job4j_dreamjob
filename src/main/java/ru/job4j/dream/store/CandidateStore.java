package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CandidateStore {

    private static final CandidateStore INST = new CandidateStore();
    private final Map<Integer, Candidate> store = new ConcurrentHashMap<>();

    private CandidateStore() {
        store.put(1, new Candidate(1, "Sergey", "Junior Java Dev", LocalDateTime.now()));
        store.put(2, new Candidate(2, "Alexsey", "Middle Java Dev", LocalDateTime.now()));
        store.put(3, new Candidate(3, "Sanya", "Senior Java Dev", LocalDateTime.now()));
    }

    public static CandidateStore instOff() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return store.values();
    }
}
