package CNT4504.client;

import CNT4504.Menu;
import CNT4504.MenuOption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintStream out;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintStream(socket.getOutputStream());
    }

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
            final Client client = new Client(socket);
            client.write(selection.toString());
            System.out.println(client.read());
        }
    }

    public void write(String data) {
        out.println(data);
    }

    public String read() throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
