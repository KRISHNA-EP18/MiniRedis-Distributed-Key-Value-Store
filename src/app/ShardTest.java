package app;

import sharding.ShardManager;

public class ShardTest {

    public static void main(String[] args) {

        ShardManager shardManager =
                new ShardManager(3);

        String[] keys = {
                "name",
                "college",
                "city",
                "cgpa",
                "branch",
                "phone",
                "email",
                "hostel",
                "course",
                "semester"
        };

        System.out.println("==================================");
        System.out.println("Shard Distribution Test");
        System.out.println("==================================");

        for (String key : keys) {

            int shard =
                    shardManager.getShard(key);

            String node =
                    shardManager.getNodeName(key);

            System.out.printf(
                    "%-10s -> Shard %d (%s)%n",
                    key,
                    shard,
                    node
            );
        }
    }
}
