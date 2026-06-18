package app;

import sharding.ConsistentHashRing;

public class ConsistentHashTest {

    public static void main(String[] args) {

        ConsistentHashRing ring =
                new ConsistentHashRing();

        ring.addNode("Node1");
        ring.addNode("Node2");
        ring.addNode("Node3");

        ring.printRing();

        String[] keys = {
                "name",
                "college",
                "city",
                "cgpa",
                "branch",
                "hostel",
                "email"
        };

        System.out.println("\nKey Distribution:");

        for(String key : keys) {

            System.out.println(
                    key
                            + " -> "
                            + ring.getNode(key)
            );
        }
    }
}