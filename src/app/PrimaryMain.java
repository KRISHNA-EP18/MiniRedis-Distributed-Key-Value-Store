package app;

import server.KeyValueServer;

public class PrimaryMain {

    public static void main(String[] args) {

        KeyValueServer primaryServer =
                new KeyValueServer(
                        5000,
                        true
                );

        primaryServer.start();
    }
}
