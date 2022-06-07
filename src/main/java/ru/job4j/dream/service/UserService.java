package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.model.User;
import ru.job4j.dream.persistence.db.UserDBStore;

import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
public class UserService {

    private final UserDBStore userDBStore;
    private final CityService cityService;

    public UserService(UserDBStore userDBStore, CityService cityService) {
        this.userDBStore = userDBStore;
        this.cityService = cityService;
    }

    public List<User> findAll() {
        List<User> users = userDBStore.findAll();
        users.forEach(
                user -> user.setCity(
                        cityService.findById(user.getCity().getId())
                )
        );
        return users;
    }

    public Optional add(User user) {
        return Optional.of(userDBStore.add(user));
    }

    public User findById(int id) {
        return userDBStore.findById(id);
    }

    public User update(User user) {
        return userDBStore.update(user);
    }
}
