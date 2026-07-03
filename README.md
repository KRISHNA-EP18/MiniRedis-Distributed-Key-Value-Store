# MiniRedis - Distributed Key-Value Store in Java

## Overview

MiniRedis is a distributed key-value store built from scratch in Java to explore the core concepts behind modern distributed systems such as Redis, DynamoDB, and Cassandra.

The project supports:

* TCP-based client-server communication
* Concurrent request processing using thread pools
* Thread-safe in-memory storage
* Persistent storage on disk
* Primary-replica replication
* Multi-node architecture
* Sharding concepts
* Consistent hashing concepts


---

## Architecture

```text
                    Client
                       |
                       | TCP
                       v
              +----------------+
              | Primary Server |
              |   Port 5000    |
              +----------------+
                 /          \
                /            \
               v              v

      +---------------+ +---------------+
      | Replica Node  | | Replica Node  |
      |   Port 5001   | |   Port 5002   |
      +---------------+ +---------------+

Each node contains:
- ConcurrentHashMap Storage
- Persistence Layer
- Thread Pool
- TCP Communication
```

---

## Features

### 1. TCP Client-Server Communication

Clients communicate with the server using TCP sockets.

Supported commands:

```text
PUT key value
GET key
DELETE key
EXISTS key
SIZE
SHOWALL
```

Example:

```text
PUT name Saketh
OK

GET name
Saketh

DELETE name
Deleted
```

---

### 2. Concurrent Request Processing

The server uses:

```java
ExecutorService
```

to process multiple client connections concurrently.

Benefits:

* Improved scalability
* Better resource utilization
* Reduced thread creation overhead

---

### 3. Thread-Safe Storage

Data is stored using:

```java
ConcurrentHashMap
```

which allows multiple threads to safely perform read and write operations concurrently.

---

### 4. Persistence

Data is automatically persisted to disk.

Files:

```text
primary-data.txt
replica1-data.txt
replica2-data.txt
```

This ensures that data survives server restarts and crashes.

---

### 5. Replication

The primary node forwards write operations to replica nodes.

Example:

```text
Client
  |
PUT name Saketh
  |
Primary
  |
+---------+
|         |
v         v
Replica1  Replica2
```

This improves:

* Fault tolerance
* Availability
* Data redundancy

---

### 6. Sharding Demonstration

The project includes a sharding module that demonstrates how data can be distributed across multiple nodes using hash-based partitioning.

```java
Math.abs(key.hashCode()) % totalShards
```

Example:

```text
name     -> Node1
college  -> Node3
city     -> Node2
```

---

### 7. Consistent Hashing Demonstration

A consistent hashing module is included to demonstrate how distributed systems minimize key movement when nodes are added or removed.

Benefits:

* Improved scalability
* Reduced data migration
* Better cluster management

---

## Project Structure

```text
MiniRedis
│
├── app
│   ├── PrimaryMain.java
│   ├── Replica1Main.java
│   ├── Replica2Main.java
│   ├── ClientMain.java
│   ├── ShardTest.java
│   └── ConsistentHashTest.java
│
├── client
│   └── KeyValueClient.java
│
├── server
│   ├── KeyValueServer.java
│   └── ClientHandler.java
│
├── store
│   ├── KeyValueStore.java
│   └── CommandProcessor.java
│
├── replication
│   └── ReplicaManager.java
│
├── storage
│   └── PersistenceManager.java
│
└── sharding
    ├── ShardManager.java
    └── ConsistentHashRing.java
```

---

## How to Run

### Start Replica 1

Run:

```java
Replica1Main
```

Output:

```text
REPLICA SERVER
Listening on port 5001
```

---

### Start Replica 2

Run:

```java
Replica2Main
```

Output:

```text
REPLICA SERVER
Listening on port 5002
```

---

### Start Primary Server

Run:

```java
PrimaryMain
```

Output:

```text
PRIMARY SERVER
Listening on port 5000
```

---

### Start Client

Run:

```java
ClientMain
```

Example:

```text
PUT name Saketh
PUT college IITBBS
SHOWALL
```

---

## Technologies Used

* Java 24
* TCP Sockets
* ConcurrentHashMap
* ExecutorService
* Multithreading
* File I/O
* Distributed Systems Concepts

---

## Future Improvements

* Request routing using consistent hashing
* Leader election
* Heartbeat mechanism
* Failure detection
* Read replicas
* Write-ahead logging (WAL)
* LRU cache
* Cluster membership management
* REST API interface

---

## Key Concepts Learned

* Distributed Systems
* Concurrency
* Multithreading
* Synchronization
* Replication
* Persistence
* Sharding
* Consistent Hashing
* TCP Networking
* Fault Tolerance
* Horizontal Scaling

---

## Author

ULISETTI SAKETH UZVAL KRISHNA

