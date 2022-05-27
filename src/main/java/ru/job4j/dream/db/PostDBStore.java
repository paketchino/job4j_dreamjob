package ru.job4j.dream.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDBStore {

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
                    posts.add(new Post(it.getInt("id"), it.getString("name")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    public Post add(Post post) {
        try (Connection cn = pool.getConnection();
            PreparedStatement preparedStatement = cn.prepareStatement("insert into post(name, city_id) values (?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, post.getName());
            preparedStatement.setInt(2, post.getCity().getId());
            preparedStatement.execute();
            try (ResultSet id = preparedStatement.executeQuery()) {
                if (id.next()) {
                    post.setId(id.getInt("1"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    public Post findById(int id) {
        Post post = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement = cn.prepareStatement("SELECT * from post where id = ?")
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet it = preparedStatement.executeQuery()) {
                if (it.next()) {
                    post = new Post(it.getInt("id"), it.getString("name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    public Post update(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement = cn.prepareStatement("update post set name = ?, city_id = ? where id = ?")
        ) {
            preparedStatement.setString(1, post.getName());
            preparedStatement.setInt(2, post.getCity().getId());
            try (ResultSet it = preparedStatement.executeQuery()) {
                if (it.next()) {
                   post.setName(it.getString("name"));
                   post.getCity().setId(it.getInt("1"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }
}
