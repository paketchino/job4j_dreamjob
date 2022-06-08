package ru.job4j.dream.persistence.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
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
            PreparedStatement preparedStatement = cn.prepareStatement("SELECT * from post")) {
            try (ResultSet it = preparedStatement.executeQuery()) {
                while (it.next()) {
                    posts.add(
                            new Post(
                                    it.getInt("id"),
                                    it.getString("name"),
                                    it.getString("describe"),
                                    it.getBoolean("visible"),
                                    new City(it.getInt("city_id")),
                                    it.getTimestamp("created").toLocalDateTime())
                    );
                }
            }
        } catch (Exception e) {
            LOGGER.catching(e);
        }
        return posts;
    }


    public Optional<Post> add(Post post) {
        try (Connection cn = pool.getConnection();
            PreparedStatement preparedStatement =
                    cn.prepareStatement("insert into POST(name, describe, visible, city_id, created) "
                                    + "values (?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, post.getName());
            preparedStatement.setString(2, post.getDesc());
            preparedStatement.setBoolean(3, post.isVisible());
            preparedStatement.setInt(4, post.getCity().getId());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(post.getCreated()));
            preparedStatement.execute();
            try (ResultSet id = preparedStatement.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Optional.of(post);
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
                            it.getBoolean("visible"),
                            new City(it.getInt("city_id")),
                            it.getTimestamp("created").toLocalDateTime()
                    );
                }
            }
        } catch (Exception e) {
            LOGGER.catching(e);
        }
        return post;
    }

    public Post update(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement = cn.prepareStatement("update POST set name = ?, describe = ?, city_id = ? where id = ?")
        ) {
            preparedStatement.setString(1, post.getName());
            preparedStatement.setString(2, post.getDesc());
            preparedStatement.setInt(3, post.getCity().getId());
            preparedStatement.setInt(4, post.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            LOGGER.catching(e);
        }
        return post;
    }
}
