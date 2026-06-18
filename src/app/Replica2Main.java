package app;

import server.KeyValueServer;

public class Replica2Main {

    public static void main(String[] args) {

        KeyValueServer replica2 =
                new KeyValueServer(
                        5002,
                        false
                );

        replica2.start();
    }
}