package CNT4504.client;

import CNT4504.Menu;
import CNT4504.MenuOption;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: <address> <port>");
            System.exit(1);
        }

        final String address = args[0];
        final int port = Integer.parseInt(args[1]);

        while (true) {
            final Menu menu = new Menu(MenuOption.values());
            final MenuOption selection = menu.getSelectedMenuOption();

            if (selection == MenuOption.QUIT) {
                break;
            }

            final Socket socket = new Socket(address, port);
            final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            final PrintStream out = new PrintStream(socket.getOutputStream());

            out.println(selection);
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
