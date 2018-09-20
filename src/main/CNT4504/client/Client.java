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

    public Client(String address, int port) throws IOException {
        this(new Socket(address, port));
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: <address> <port>");
            System.exit(1);
        }

        try {
            final String address = args[0];
            final int port = Integer.parseInt(args[1]);

            while (true) {
                final Menu menu = new Menu(MenuOption.values());
                final MenuOption selection = menu.getSelectedMenuOption();

                if (selection == MenuOption.QUIT) {
                    break;
                }

                final Client client = new Client(address, port);
                client.write(selection.toString());
                System.out.println(client.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
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
