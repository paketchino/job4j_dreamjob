package ru.job4j.dream.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.Candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
                                    it.getString("desc"),
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


    public Candidate add(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement =
                     cn.prepareStatement("insert into candidate(name, desc, visible, created) "
                                     + "values (?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)
            ) {
            preparedStatement.setString(1, candidate.getName());
            preparedStatement.setString(2, candidate.getDesc());
            preparedStatement.setBoolean(3, candidate.isVisible());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(candidate.getTimeFormat()));
            preparedStatement.execute();
            try (ResultSet id = preparedStatement.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt("1"));
                }
            }
        } catch (Exception e) {
            LOGGER.catching(e);
        }
        return candidate;
    }

    public Candidate findById(int id) {
        Candidate candidate = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement =
                     cn.prepareStatement("select * from candidate where id = ? ")
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet it = preparedStatement.executeQuery()) {
                if (it.next()) {
                    candidate = new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("desc"),
                            it.getBoolean("visible"),
                            it.getTimestamp("created").toLocalDateTime()
                    );
                }
            }
        } catch (Exception e) {
            LOGGER.catching(e);
        }
        return candidate;
    }


    public Candidate update(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement =
                     cn.prepareStatement("update candidate set name = ?, desc = ? where id = ?")
        ) {
            preparedStatement.setString(1, candidate.getName());
            preparedStatement.setString(2, candidate.getDesc());
            preparedStatement.setInt(3, candidate.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            LOGGER.catching(e);
        }
        return candidate;
    }
}
