package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.db.CandidateDB;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.CandidateStore;

import java.util.Collection;

@ThreadSafe
@Service
public class CandidateService {

    private final CandidateStore candidateStore;

    private final CandidateDB candidateDB;

    public CandidateService(CandidateStore candidateStore, CandidateDB candidateDB) {
        this.candidateStore = candidateStore;
        this.candidateDB = candidateDB;
    }

    public void add(Candidate candidate) {
        candidateStore.add(candidate);
    }

    public Candidate update(Candidate candidate) {
        candidate.setVisible(true);
        return candidateDB.update(candidate);
    }

    public Candidate findById(int id) {
        return candidateDB.findById(id);
    }

    public Collection<Candidate> findAll()   {
        return candidateDB.findAll();
    }
}
