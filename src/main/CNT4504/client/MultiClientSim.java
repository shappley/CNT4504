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

        for (int i = 0; i < numberOfClients; i++) {
            clientThreads[i] = new Thread(() -> {
                try {
                    long startTime = System.currentTimeMillis();
                    final Client client = new Client(args[0], port);
                    client.write(option.toString());
                    String response = client.read();
                    long endTime = System.currentTimeMillis();
                    System.out.println((endTime - startTime) + "ms");
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
