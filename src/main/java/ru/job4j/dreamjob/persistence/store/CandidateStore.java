package ru.job4j.dreamjob.persistence.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class CandidateStore {

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private AtomicInteger candiId = new AtomicInteger(4);

    private CandidateStore() {
        candidates.put(1, new Candidate(1, "Sergey", "Junior Java Dev", new byte[1024], true, LocalDateTime.now()));
        candidates.put(2, new Candidate(2, "Alexsey", "Middle Java Dev", new byte[1024], true, LocalDateTime.now()));
        candidates.put(3, new Candidate(3, "Sanya", "Senior Java Dev", new byte[1024], true, LocalDateTime.now()));
    }

    public void add(Candidate candidate) {
        candidate.setTimeFormat(LocalDateTime.now());
        candidates.put(candidate.setId(candiId.getAndIncrement()), candidate);
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }

    public Candidate updateCandidate(Candidate candidate) {
        candidate.setTimeFormat(LocalDateTime.now());
        return candidates.replace(candidate.getId(), candidate);
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
