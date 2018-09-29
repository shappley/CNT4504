package CNT4504.client;

import CNT4504.MenuOption;

import java.io.IOException;

public class MultiClientSim {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("<address><port><option><number of clients>");
            System.exit(1);
        }

        final int port = Integer.parseInt(args[1]);
        final int numberOfClients = Integer.parseInt(args[3]);
        final MenuOption option = MenuOption.valueOf(args[2]);
        final Thread[] clientThreads = new Thread[numberOfClients];

        /*
         * Ahuja:
         * Ensure that you take the time in each client thread just before
         * you send the request over the socket to the server and again
         * just after the last byte of the response has been returned
         * by the server to the client.
         * Do not include time to display the results on the client side
         */
        for (int i = 0; i < numberOfClients; i++) {
            final int n = i;
            clientThreads[i] = new Thread(() -> {
                try {
                    final Client client = new Client(args[0], port);
                    long startTime = System.currentTimeMillis();
                    client.write(option.toString());
                    String response = client.read();
                    long endTime = System.currentTimeMillis();
                    System.out.println(n + "," + (endTime - startTime));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        for (Thread t : clientThreads) {
            t.start();
        }
    }
}
