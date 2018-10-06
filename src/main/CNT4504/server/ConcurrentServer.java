package CNT4504.server;

import java.io.IOException;
import java.net.Socket;

/**
 * CNT4504 - Fall 2018
 * Group 6
 * Stephen Shappley
 * Craig Frazier
 * Jae Moon
 * Jonathon Depaul
 */
public class ConcurrentServer extends Server {
    public ConcurrentServer(int port) throws IOException {
        super(port);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Must supply port");
            System.exit(1);
        }

        final Server server = new ConcurrentServer(Integer.parseInt(args[0]));
        server.run();
    }

    @Override
    public void serveClient(Socket client) throws IOException {
        new Thread(() -> {
            try {
                super.serveClient(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
