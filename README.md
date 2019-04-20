# CNT4504 - Project 1 and 2
The purpose of these projects was to show how the response time of a server is affected
by the size of the request and the number of concurrent clients.

Three different server types were created: 
- iterative (one thread handling all requests, requiring client queueing), 
- concurrent (thread-per-client),
- and thread-pool (shared pool of reused threads to save overhead of thread creation)

### Running the Project
Server must be started before the client.

- Iterative server
  - `java CNT4504/server/Server <port number>`
- Simple concurrent server
  - `java CNT4504/server/ConcurrentServer <port>`
- Pooled server
  - `java CNT4504/server/PooledServer <port><pool size>`
- Single client
  - `java CNT4505/client/Client <address><port>`
- Concurrent clients
  - `java CNT4504/client/MultiClientSim <address><port><command><number of clients>`