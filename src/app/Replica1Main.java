package app;

import server.KeyValueServer;

public class Replica1Main {

    public static void main(String[] args) {

        KeyValueServer replica1 =
                new KeyValueServer(
                        5001,
                        false
                );

        replica1.start();
    }
}
