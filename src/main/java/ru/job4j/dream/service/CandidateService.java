package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.CandidateStore;

import java.util.Collection;

@ThreadSafe
@Service
public class CandidateService {

    private final CandidateStore candidateStore;

    public CandidateService(CandidateStore candidateStore) {
        this.candidateStore = candidateStore;
    }

    public void add(Candidate candidate) {
        candidateStore.add(candidate);
    }

    public Candidate update(Candidate candidate) {
        return candidateStore.updateCandidate(candidate);
    }

    public Candidate findById(int id) {
        return candidateStore.findById(id);
    }

    public Collection<Candidate> findAll()   {
        return candidateStore.findAll();
    }
}
