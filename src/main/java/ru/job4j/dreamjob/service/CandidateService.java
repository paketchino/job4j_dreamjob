package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.persistence.db.CandidateDB;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.persistence.store.CandidateStore;

import java.util.Collection;
import java.util.Optional;

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

    public Optional<Candidate> update(Candidate candidate) {
        candidate.setVisible(true);
        return candidateDB.update(candidate);
    }

    public Optional<Candidate> findById(int id) {
        return candidateDB.findById(id);
    }

    public Collection<Candidate> findAll()   {
        return candidateDB.findAll();
    }
}
