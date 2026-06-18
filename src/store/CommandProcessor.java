package store;

import replication.ReplicaManager;

public class CommandProcessor {

    private final KeyValueStore store;
    private final ReplicaManager replicaManager;

    public CommandProcessor(
            KeyValueStore store,
            ReplicaManager replicaManager
    ) {
        this.store = store;
        this.replicaManager = replicaManager;
    }

    public String process(String command) {

        String[] parts =
                command.trim().split("\\s+");

        if(parts.length == 0)
            return "Invalid Command";

        String operation =
                parts[0].toUpperCase();

        switch(operation) {

            case "PUT":

                if(parts.length < 3)
                    return "Usage: PUT key value";

                store.put(
                        parts[1],
                        parts[2]
                );

                if(replicaManager != null) {
                    replicaManager.replicate(command);
                }

                return "OK";

            case "GET":

                if(parts.length < 2)
                    return "Usage: GET key";

                String value =
                        store.get(parts[1]);

                return value == null
                        ? "NULL"
                        : value;

            case "DELETE":

                if(parts.length < 2)
                    return "Usage: DELETE key";

                boolean deleted =
                        store.delete(parts[1]);

                if(replicaManager != null) {
                    replicaManager.replicate(command);
                }

                return deleted
                        ? "Deleted"
                        : "Key Not Found";

            case "SIZE":

                return String.valueOf(
                        store.size()
                );

            case "EXISTS":

                if(parts.length < 2)
                    return "Usage: EXISTS key";

                return String.valueOf(
                        store.exists(parts[1])
                );

            case "SHOWALL":

                return store.toString();

            default:

                return "Unknown Command";
        }
    }
}