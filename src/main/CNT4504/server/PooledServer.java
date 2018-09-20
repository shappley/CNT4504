package CNT4504.server;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PooledServer extends Server {
    private ExecutorService pool;

    public PooledServer(int port, int poolSize) throws IOException {
        super(port);
        pool = Executors.newFixedThreadPool(poolSize);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Must supply port and pool size");
            System.exit(1);
        }

        final Server server = new PooledServer(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        server.run();
    }

    @Override
    public void serveClient(Socket client) throws IOException {
        pool.submit(() -> {
            try {
                super.serveClient(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
