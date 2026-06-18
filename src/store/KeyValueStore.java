package store;

import storage.PersistenceManager;

import java.util.concurrent.ConcurrentHashMap;

public class KeyValueStore {

    private final ConcurrentHashMap<String, String> store;
    private final PersistenceManager persistence;

    public KeyValueStore(String fileName) {

        persistence =
                new PersistenceManager(fileName);

        store =
                persistence.load();

        System.out.println(
                "Loaded "
                        + store.size()
                        + " records from "
                        + fileName
        );
    }

    public void put(String key, String value) {

        store.put(key, value);

        persistence.save(store);
    }

    public String get(String key) {
        return store.get(key);
    }

    public boolean delete(String key) {

        boolean deleted =
                store.remove(key) != null;

        persistence.save(store);

        return deleted;
    }

    public boolean exists(String key) {
        return store.containsKey(key);
    }

    public int size() {
        return store.size();
    }

    @Override
    public String toString() {
        return store.toString();
    }
}