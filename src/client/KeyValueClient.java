package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class KeyValueClient {

    private final String host;
    private final int port;

    public KeyValueClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {

        try (

                Socket socket =
                        new Socket(host, port);

                BufferedReader serverReader =
                        new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream()
                                )
                        );

                PrintWriter serverWriter =
                        new PrintWriter(
                                socket.getOutputStream(),
                                true
                        );

                Scanner scanner =
                        new Scanner(System.in)

        ) {

            System.out.println(
                    "Connected to MiniRedis Server"
            );

            while (true) {

                System.out.print("> ");

                String command =
                        scanner.nextLine();

                if(command.equalsIgnoreCase("EXIT")) {
                    break;
                }

                serverWriter.println(command);

                String response =
                        serverReader.readLine();

                System.out.println(response);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
