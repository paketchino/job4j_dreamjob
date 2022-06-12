package ru.job4j.dream.persistence.db;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.dream.Main;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.User;

import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDBStoreTest {

    @Ignore
    @Test
    public void whenUserAdd() {
        UserDBStore userDBStore = new UserDBStore(new Main().loadPool());
        User user = new User(1, "Roman", "roman@gmail.com", "password", new City(3, "London"));
        Optional<User> userInDb = userDBStore.add(user);
        assertThat(userInDb.get().getEmail(), is(user.getEmail()));
    }

    @Test
    public void whenUserUpdate() {
    }
}