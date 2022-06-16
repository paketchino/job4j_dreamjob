package ru.job4j.dreamjob.persistence.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CandidateDB {

    private static final Logger LOGGER = LogManager.getLogger();

    private final BasicDataSource pool;

    public CandidateDB(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Candidate> findAll() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement =
                     cn.prepareStatement("SELECT * from candidate")) {
            try (ResultSet it = preparedStatement.executeQuery()) {
                while (it.next()) {
                    candidates.add(
                            new Candidate(
                                    it.getInt("id"),
                                    it.getString("name"),
                                    it.getString("describe"),
                                    it.getBytes("photo"),
                                    it.getBoolean("visible"),
                                    it.getTimestamp("created").toLocalDateTime())
                            );
                }
            }
        } catch (Exception e) {
            LOGGER.catching(e);
        }
        return candidates;
    }


    public Optional<Candidate> add(Candidate candidate) {
        Optional<Candidate> rsl = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement =
                     cn.prepareStatement("insert into CANDIDATE(name, describe, photo, visible, created) values (?, ?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)
            ) {
            preparedStatement.setString(1, candidate.getName());
            preparedStatement.setString(2, candidate.getDesc());
            preparedStatement.setBytes(3, candidate.getPhoto());
            preparedStatement.setBoolean(4, candidate.isVisible());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(candidate.getTimeFormat()));
            preparedStatement.execute();
            try (ResultSet id = preparedStatement.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
            rsl = Optional.of(candidate);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rsl;
    }

    public Optional<Candidate> findById(int id) {
        Optional<Candidate> rsl = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement =
                     cn.prepareStatement("select * from candidate where id = ? ")
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet it = preparedStatement.executeQuery()) {
                if (it.next()) {
                    rsl = Optional.of(new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("describe"),
                            it.getBytes("photo"),
                            it.getBoolean("visible"),
                            it.getTimestamp("created").toLocalDateTime()
                    ));
                }
            }
        } catch (Exception e) {
            LOGGER.catching(e);
        }
        return rsl;
    }


    public Optional<Candidate> update(Candidate candidate) {
        Optional<Candidate> rsl = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement =
                     cn.prepareStatement("update candidate set name = ?, describe = ? where id = ?")
        ) {
            preparedStatement.setString(1, candidate.getName());
            preparedStatement.setString(2, candidate.getDesc());
            preparedStatement.setInt(3, candidate.getId());
            preparedStatement.executeUpdate();
            rsl = Optional.of(candidate);
        } catch (Exception e) {
            LOGGER.catching(e);
        }
        return rsl;
    }
}
