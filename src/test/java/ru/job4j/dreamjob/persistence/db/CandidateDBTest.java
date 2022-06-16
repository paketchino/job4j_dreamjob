package ru.job4j.dreamjob.persistence.db;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Optional;

public class CandidateDBTest {

    @Test
    public void whenCreateCandidate() {
        CandidateDB candidateDB = new CandidateDB(new Main().loadPool());
        Candidate candidate = new Candidate(1, "name", "desc", new byte[1024], true, LocalDateTime.now());
        candidateDB.add(candidate);
        Optional<Candidate> candidateId = candidateDB.findById(candidate.getId());
        assertThat(candidateId.get().getName(), is(candidate.getName()));

    }

    @Ignore
    @Test
    public void whenTryToFindCandidateWithoutId() {
        CandidateDB candidateDB = new CandidateDB(new Main().loadPool());
        Candidate candidate = new Candidate(22, "Sergevbvb", "Java Doc", new byte[1024], true, LocalDateTime.now());
        Optional<Candidate> candidateId = candidateDB.findById(candidate.getId());
        assertThat(candidateId, is(Optional.empty()));
    }

    @Test
    public void whenUpdateCandidate() {
        CandidateDB candidateDB = new CandidateDB(new Main().loadPool());
        Candidate candidate =
                new Candidate(1, "name", "desc", new byte[1024], true, LocalDateTime.now());
        candidateDB.add(candidate);
        Candidate candidateUpdate =
                new Candidate(candidate.getId(), "Sergey", "Java Senior", new byte[1024], true, LocalDateTime.now());
        candidateDB.update(candidateUpdate);
        Optional<Candidate> candidateInDB = candidateDB.findById(candidate.getId());
        assertThat(candidateInDB.get().getName(), is(candidateUpdate.getName()));
    }
}