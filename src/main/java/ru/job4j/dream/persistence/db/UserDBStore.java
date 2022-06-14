package ru.job4j.dream.persistence.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDBStore {

    private static final Logger LOGGER = LogManager.getLogger();

    private final BasicDataSource pool;

    public UserDBStore(BasicDataSource pool) {
        this.pool = pool;
    }


    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement = cn.prepareStatement("select * from USERS")) {
            try (ResultSet it = preparedStatement.executeQuery()) {
                while (it.next()) {
                    users.add(
                            new User(
                                    it.getInt("id"),
                                    it.getString("name_users"),
                                    it.getString("email"),
                                    it.getString("password"),
                                    new City(it.getInt("city_id"))
                            )
                    );
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }

    public Optional<User> add(User user) {
        Optional<User> rsl = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement =
                     cn.prepareStatement("insert into USERS(name_users, email, password, city_id) values (?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getCity().getId());
            preparedStatement.execute();
            try (ResultSet id = preparedStatement.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
            rsl = Optional.of(user);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rsl;
    }


    public Optional<User> findById(int id) {
        Optional<User> rsl = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement =
                     cn.prepareStatement("select * from USERS where id = ?")
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet it = preparedStatement.executeQuery()) {
                if (it.next()) {
                    rsl = Optional.of(new User(
                            it.getInt("id"),
                            it.getString("name_users"),
                            it.getString("email"),
                            it.getString("password"),
                            new City(it.getInt("city_id"))
                    ));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rsl;
    }


    public Optional<User> update(User user) {
        Optional<User> rsl = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement preparedStatement =
                     cn.prepareStatement("update USERS set NAME_USERS = ?, EMAIL = ?, PASSWORD = ?, CITY_ID = ? where ID = ?")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getCity().getId());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.execute();
            rsl = Optional.of(user);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rsl;
    }
}
