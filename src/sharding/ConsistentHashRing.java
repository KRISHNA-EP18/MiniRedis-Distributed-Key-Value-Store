package sharding;

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashRing {

    private final SortedMap<Integer, String> ring;

    public ConsistentHashRing() {
        ring = new TreeMap<>();
    }

    public void addNode(String nodeName) {

        int hash =
                Math.abs(nodeName.hashCode());

        ring.put(hash, nodeName);

        System.out.println(
                nodeName
                        + " added at hash "
                        + hash
        );
    }

    public String getNode(String key) {

        if (ring.isEmpty()) {
            return null;
        }

        int hash =
                Math.abs(key.hashCode());

        SortedMap<Integer, String> tailMap =
                ring.tailMap(hash);

        Integer nodeHash =
                tailMap.isEmpty()
                        ? ring.firstKey()
                        : tailMap.firstKey();

        return ring.get(nodeHash);
    }

    public void printRing() {

        System.out.println("\nHash Ring:");

        for (var entry : ring.entrySet()) {

            System.out.println(
                    entry.getKey()
                            + " -> "
                            + entry.getValue()
            );
        }
    }
}
