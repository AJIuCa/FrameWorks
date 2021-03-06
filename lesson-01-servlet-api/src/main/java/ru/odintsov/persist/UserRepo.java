package ru.odintsov.persist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepo {

    private Map<Long, User> userMap = new ConcurrentHashMap<>();

    private AtomicLong identity = new AtomicLong(0);

    public Map<Long, User> getUserMap() {
        return userMap;
    }

    public void nameId () {

        System.out.println(userMap.entrySet());

    }

    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    public User findById (long id) {
        return userMap.get(id);
    }

    public void insert (User user) {
        long id = identity.incrementAndGet();
        user.setId(id);
        userMap.put(id, user);
    }

    public void update (User user) {
        userMap.put(user.getId(),user);

    }

    public void delete (long id) {
        userMap.remove(id);
    }

    public int size() {
        int i = userMap.size();
        return i;
    }

}
