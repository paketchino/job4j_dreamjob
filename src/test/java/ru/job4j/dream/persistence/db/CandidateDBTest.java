package ru.job4j.dream.persistence.db;

import org.junit.Test;
import ru.job4j.dream.Main;
import ru.job4j.dream.model.Candidate;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

public class CandidateDBTest {

    @Test
    public void whenCreateCandidate() {
        CandidateDB candidateDB = new CandidateDB(new Main().loadPool());
        Candidate candidate = new Candidate(1, "name", "desc", new byte[1024], true, LocalDateTime.now());
        candidateDB.add(candidate);
        Candidate candidateId = candidateDB.findById(candidate.getId());
        assertThat(candidateId.getName(), is(candidate.getName()));

    }

    @Test
    public void whenTryToFindCandidateWithoutId() {
        CandidateDB candidateDB = new CandidateDB(new Main().loadPool());
        Candidate candidate = new Candidate(16, "Roman", "Course", new byte[1024], true, LocalDateTime.now());
        Candidate candidateId = candidateDB.findById(candidate.getId());
        assertNull(candidateId);
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
        Candidate candidateInDB = candidateDB.findById(candidate.getId());
        assertThat(candidateInDB.getName(), is(candidateUpdate.getName()));
    }
}