package CNT4504.server;

import CNT4504.MenuOption;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;

    public Server(int port) throws IOException {
        this.server = new ServerSocket(port);
    }

    public static void main(String[] args) throws Exception {
        final ServerSocket server = new ServerSocket(9001);
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

    public Socket client() throws IOException {
        return server.accept();
    }

    public void write(Socket client, String data) throws IOException {
        write(new DataOutputStream(client.getOutputStream()), data);
    }

    public void write(DataOutputStream stream, String data) throws IOException {
        stream.writeChars(data);
    }

    public String read(DataInputStream stream) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(stream.readChar());
        return sb.toString();
    }
}
