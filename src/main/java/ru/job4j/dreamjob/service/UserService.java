package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.persistence.db.UserDBStore;

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

    public Optional<User> findUserByEmailAndPwd(String email, String password) {
        return find(email, password);
    }

    private Optional<User> find(String email, String password) {
        Optional<User> findUser = Optional.empty();
        List<User> users = userDBStore.findAll();
        for (User user : users) {
            if (user.getEmail().equals(email)
                    && user.getPassword().equals(password)) {
                findUser = Optional.of(user);
            }
        }
        return findUser;
    }

    public Optional<User> findById(int id) {
        return userDBStore.findById(id);
    }

    public Optional<User> update(User user) {
        return userDBStore.update(user);
    }
}
