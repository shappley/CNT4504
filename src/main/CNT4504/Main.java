package CNT4504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(executeCommand());

    }

    public static String executeCommand() {

        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "time", "/t");

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
