package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CandidatesStore {

    private static final CandidatesStore INST = new CandidatesStore();
    private final Map<Integer, Candidate> store = new ConcurrentHashMap<>();

    private LocalDate date = LocalDate.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
    private String text = date.format(formatter);
    private LocalDate parsedDate = LocalDate.parse(text, formatter);

    private CandidatesStore() {
        store.put(1, new Candidate(1, "Java Junior", "desc1", parsedDate));
        store.put(2, new Candidate(2, "Java Middle", "desc2", parsedDate));
        store.put(3, new Candidate(3, "Java Senior", "desc3", parsedDate));
    }

    public static CandidatesStore instOff() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return store.values();
    }
}
