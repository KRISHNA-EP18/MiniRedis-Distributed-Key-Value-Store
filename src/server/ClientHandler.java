package server;

import store.CommandProcessor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket client;
    private final CommandProcessor processor;

    public ClientHandler(Socket client, CommandProcessor processor) {
        this.client = client;
        this.processor = processor;
    }

    @Override
    public void run() {

        System.out.println(
                "Handling client on thread: "
                        + Thread.currentThread().getName()
        );

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        client.getInputStream()
                                )
                        );

                PrintWriter writer =
                        new PrintWriter(
                                client.getOutputStream(),
                                true
                        )
        ) {

            String command;

            while ((command = reader.readLine()) != null) {

                System.out.println(
                        "[" + Thread.currentThread().getName() + "] "
                                + command
                );

                String response =
                        processor.process(command);

                writer.println(response);
            }

        } catch (Exception e) {

            System.out.println(
                    "Client disconnected."
            );

        } finally {

            try {
                client.close();
            } catch (Exception ignored) {
            }

        }
    }
}