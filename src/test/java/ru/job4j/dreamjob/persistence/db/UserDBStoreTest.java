package ru.job4j.dreamjob.persistence.db;


import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.User;

import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDBStoreTest {

    @Test
    public void whenUserAdd() {
        UserDBStore userDBStore = new UserDBStore(new Main().loadPool());
        User user = new User(1, "Roman", "roman@gmail.com", "password", new City(3, "London"));
        Optional<User> userInDb = userDBStore.add(user);
        assertThat(userInDb.get().getEmail(), is(user.getEmail()));
    }

    @Test
    public void whenUserUpdate() {
        UserDBStore userDBStore = new UserDBStore(new Main().loadPool());
        User user = new User(5, "Alexey", "alexsey@gmail.com", "password123", new City(3, "London"));
        userDBStore.add(user);
        User updateUser = new User(user.getId(), "Sergey", "ghdasd@gmail.com", "Gelendjik228", user.getCity());
        userDBStore.update(updateUser);
        assertThat(userDBStore.findAll().get(5).getEmail(), is(updateUser.getEmail()));
    }
}