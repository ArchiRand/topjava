package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {

    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;
    private Map<Integer, User> USERS = new ConcurrentHashMap<>();
    private AtomicInteger ai = new AtomicInteger(0);

    {
        save(new User(1, "User", "user@yandex.ru", "password", Role.ROLE_USER));
        save(new User(1, "Admin", "admin@yandex.ru", "admin", Role.ROLE_ADMIN));
    }



    @Override
    public User save(User user) {
        int x = ai.incrementAndGet();
        if (user.isNew()) {
            user.setId(x);
        }
        USERS.put(x, user);
        LOG.info("save " + user);
        return user;
    }

    @Override
    public User get(int id) {
        User user = USERS.get(id);
        LOG.info("get " + id);
        return user;
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        if (!USERS.isEmpty()) {
            return USERS.values().
                    stream().
                    filter(user -> user.getName().compareTo(user.getName()) < 0).
                    collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        return USERS.values().
                stream().
                filter(user -> user.getEmail().equals(email)).
                findFirst().get();
    }

    @Override
    public boolean delete(int id) {
        User user = USERS.remove(id);
        LOG.info("delete " + id);
        return user != null;
    }

}
