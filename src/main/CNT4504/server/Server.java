package CNT4504.server;

import CNT4504.MenuOption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * CNT4504 - Fall 2018
 * Group 6
 * Stephen Shappley
 * Craig Frazier
 * Jae Moon
 * Jonathon Depaul
 */
public class Server implements Runnable {
    private ServerSocket server;

    public Server(int port) throws IOException {
        this.server = new ServerSocket(port);
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Must supply port");
            System.exit(1);
        }

        final Server server = new Server(Integer.parseInt(args[0]));
        server.run();
    }

    @Override
    public void run() {
        while (true) {
            try {
                final Socket client = getNextClient();
                serveClient(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getNextClient() throws IOException {
        System.out.println("Waiting for next client");
        final Socket client = server.accept();
        System.out.println("Connected to client");
        return client;
    }

    public void serveClient(Socket client) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        final PrintStream out = new PrintStream(client.getOutputStream());
        System.out.println("Reading client request");
        final String read = in.readLine();
        final MenuOption selection = MenuOption.valueOf(read);
        System.out.println("Executing " + selection);
        final String response = executeCommand(selection);
        System.out.println("Writing response");
        out.println(response);
        out.close();
    }

    public String executeCommand(MenuOption option) {
        ProcessBuilder pb = new ProcessBuilder(option.getCommand().split(" "));

        try (InputStreamReader isr = new InputStreamReader(pb.start().getInputStream());
             BufferedReader in = new BufferedReader(isr);
        ) {
            StringBuilder builder = new StringBuilder();
            String s;
            while ((s = in.readLine()) != null) {
                builder.append(s).append("\n");
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
