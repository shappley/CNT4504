package CNT4504.client;

import CNT4504.Menu;
import CNT4504.MenuOption;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private String address;

    public Client(String address, int port) throws IOException {
        this.address = address;
        this.socket = new Socket(address, port);
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: <address> <port>");
            System.exit(1);
        }

        int clients = 1, port = 9001;
        String address = args[0];
        port = Integer.parseInt(args[1]);

        while (true) {
            final Menu menu = new Menu(MenuOption.values());
            final MenuOption selection = menu.getSelectedMenuOption();

            if (selection == MenuOption.QUIT) {
                break;
            }

            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 75; i++) {
                System.out.println("client " + i);
                final Socket socket = new Socket(address, port);
                final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                final PrintStream out = new PrintStream(socket.getOutputStream());

                out.println(selection);
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            }
            long finishTime = System.currentTimeMillis();

            System.out.println("Total time: " + (finishTime - startTime) + "ms");
            System.out.println("Average time: " + ((finishTime - startTime) / 50.0) + "ms");
        }
    }

    public String getAddress() {
        return address;
    }

    public Socket getSocket() {
        return socket;
    }

    public void write(String data) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.write(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StringBuilder read() {
        try {
            InputStreamReader is = new InputStreamReader(socket.getInputStream());
            BufferedReader in = new BufferedReader(is);
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                builder.append(line);
            }
            return builder;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
