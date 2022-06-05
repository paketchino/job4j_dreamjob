package ru.job4j.dream.persistence.db;

import org.junit.Test;
import ru.job4j.dream.Main;
import ru.job4j.dream.model.Candidate;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
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
}