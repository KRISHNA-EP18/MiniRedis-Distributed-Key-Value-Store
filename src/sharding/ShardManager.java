package sharding;

public class ShardManager {

    private final int totalShards;

    public ShardManager(int totalShards) {
        this.totalShards = totalShards;
    }

    public int getShard(String key) {

        return Math.abs(
                key.hashCode()
        ) % totalShards;
    }

    public String getNodeName(String key) {

        int shard = getShard(key);

        return switch (shard) {
            case 0 -> "Node1";
            case 1 -> "Node2";
            case 2 -> "Node3";
            default -> "Unknown";
        };
    }
}