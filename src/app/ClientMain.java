package app;

import client.KeyValueClient;

public class ClientMain {

    public static void main(String[] args) {

        KeyValueClient client =
                new KeyValueClient(
                        "localhost",
                        5000
                );

        client.start();
    }
}