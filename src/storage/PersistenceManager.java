package storage;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PersistenceManager {

    private final String fileName;

    public PersistenceManager(String fileName) {
        this.fileName = fileName;
    }

    public void save(
            ConcurrentHashMap<String, String> store
    ) {

        try (
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(fileName)
                        )
        ) {

            for (Map.Entry<String, String> entry
                    : store.entrySet()) {

                writer.write(
                        entry.getKey()
                                + "="
                                + entry.getValue()
                );

                writer.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConcurrentHashMap<String, String> load() {

        ConcurrentHashMap<String, String> store =
                new ConcurrentHashMap<>();

        File file = new File(fileName);

        if (!file.exists()) {
            return store;
        }

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(file)
                        )
        ) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts =
                        line.split("=", 2);

                if (parts.length == 2) {

                    store.put(
                            parts[0],
                            parts[1]
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return store;
    }
}