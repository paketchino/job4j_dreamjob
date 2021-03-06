package ru.job4j.dreamjob.persistence.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostDBStore {


    private static final Logger LOGGER = LogManager.getLogger();

    private final BasicDataSource pool;


    public PostDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
            PreparedStatement preparedStatement =
                    cn.prepareStatement("SELECT * from post")) {
            try (ResultSet it = preparedStatement.executeQuery()) {
                while (it.next()) {
                    posts.add(
                            new Post(
                                    it.getInt("id"),
                                    it.getString("name"),
                                    it.getString("describe"),
                                    new City(it.getInt("city_id"), null),
                                    it.getTimestamp("created").toLocalDateTime(),
                                    it.getBoolean("visible"))
                    );
                }
            }
        } catch (Exception e) {
            LOGGER.catching(e);
        }
        return posts;
    }


    public Optional<Post> add(Post post) {
        Optional<Post> rsl = Optional.empty();
        try (Connection cn = pool.getConnection();
            PreparedStatement preparedStatement =
                    cn.prepareStatement("insert into POST(name, describe, city_id, created, visible) "
                                    + "values (?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, post.getName());
            preparedStatement.setString(2, post.getDesc());
            preparedStatement.setInt(3, post.getCity().getId());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            preparedStatement.setBoolean(5, post.isVisible());
            preparedStatement.execute();
            try (ResultSet id = preparedStatement.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
            rsl = Optional.of(post);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rsl;
    }

    public Post findById(int id) {
        Post post = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement =
                     cn.prepareStatement("SELECT * from POST where id = ?")
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet it = preparedStatement.executeQuery()) {
                if (it.next()) {
                    post = new Post(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("describe"),
                            new City(it.getInt("city_id"), null),
                            it.getTimestamp("created").toLocalDateTime(),
                            it.getBoolean("visible")
                    );
                }
            }
        } catch (Exception e) {
            LOGGER.catching(e);
        }
        return post;
    }

    public Optional<Post> update(Post post) {
        Optional<Post> rsl = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement = cn.prepareStatement("update POST set name = ?, describe = ?, city_id = ? where id = ?")
        ) {
            preparedStatement.setString(1, post.getName());
            preparedStatement.setString(2, post.getDesc());
            preparedStatement.setInt(3, post.getCity().getId());
            preparedStatement.setInt(4, post.getId());
            preparedStatement.execute();
            rsl = Optional.of(post);
        } catch (Exception e) {
            LOGGER.catching(e);
        }
        return rsl;
    }
}
