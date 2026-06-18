package server;

import replication.ReplicaManager;
import store.CommandProcessor;
import store.KeyValueStore;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KeyValueServer {

    private final int port;
    private final boolean primary;

    private final CommandProcessor processor;
    private final ExecutorService threadPool;

    public KeyValueServer(
            int port,
            boolean primary
    ) {

        this.port = port;
        this.primary = primary;

        String fileName;

        if (primary) {
            fileName = "primary-data.txt";
        } else if (port == 5001) {
            fileName = "replica1-data.txt";
        } else {
            fileName = "replica2-data.txt";
        }

        KeyValueStore store =
                new KeyValueStore(fileName);

        ReplicaManager replicaManager = null;

        if (primary) {

            replicaManager =
                    new ReplicaManager(
                            List.of(5001, 5002)
                    );
        }

        this.processor =
                new CommandProcessor(
                        store,
                        replicaManager
                );

        this.threadPool =
                Executors.newFixedThreadPool(10);
    }

    public void start() {

        try (
                ServerSocket serverSocket =
                        new ServerSocket(port)
        ) {

            System.out.println(
                    "================================="
            );

            System.out.println(
                    primary
                            ? "PRIMARY SERVER"
                            : "REPLICA SERVER"
            );

            System.out.println(
                    "Listening on port "
                            + port
            );

            System.out.println(
                    "================================="
            );

            while (true) {

                Socket client =
                        serverSocket.accept();

                System.out.println(
                        "Client Connected: "
                                + client.getInetAddress()
                );

                threadPool.submit(
                        new ClientHandler(
                                client,
                                processor
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            threadPool.shutdown();

        }
    }
}