package CNT4504.server;

import CNT4504.MenuOption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Must supply port");
            System.exit(1);
        }
        final ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
        while (true) {
            final Socket client = server.accept();
            final BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            final PrintStream out = new PrintStream(client.getOutputStream());

            final MenuOption selection = MenuOption.valueOf(in.readLine());

            System.out.println(selection);

            final String response = Server.executeCommand(selection);
            out.println(response);
            out.close();
        }
    }

    public static String executeCommand(MenuOption option) {
        ProcessBuilder pb = new ProcessBuilder(option.getCommand().split(" "));

        try (InputStreamReader isr = new InputStreamReader(pb.start().getInputStream());
             BufferedReader in = new BufferedReader(isr);
        ) {
            StringBuilder builder = new StringBuilder();
            String s;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
                builder.append(s);
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
