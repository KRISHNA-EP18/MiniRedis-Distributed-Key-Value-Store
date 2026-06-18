package replication;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ReplicaManager {

    private final List<Integer> replicaPorts;

    public ReplicaManager(
            List<Integer> replicaPorts
    ) {
        this.replicaPorts = replicaPorts;
    }

    public void replicate(String command) {

        for(int port : replicaPorts) {

            try(
                    Socket socket =
                            new Socket(
                                    "localhost",
                                    port
                            );

                    PrintWriter writer =
                            new PrintWriter(
                                    socket.getOutputStream(),
                                    true
                            )
            ) {

                writer.println(command);

            } catch(Exception e) {

                System.out.println(
                        "Replica on port "
                                + port
                                + " unavailable"
                );
            }
        }
    }
}