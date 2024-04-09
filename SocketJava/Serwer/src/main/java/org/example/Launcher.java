package org.example;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.nio.file.Files;

public class Launcher {
    public Launcher() {
        try {
            handleConnections();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void handleConnections() throws IOException {
        ServerSocket ss = new ServerSocket(6700);
        File file1 = new File("src/main/java/org/example/start.table.txt");
        File file2 = new File("src/main/java/org/example/start.Huffman.txt");

        while (true) {
            Socket socket = ss.accept();
            OutputStream os = socket.getOutputStream();
            Files.copy(file1.toPath(), os);
            os.write("ZMIANA PLIKU\n".getBytes());
            Files.copy(file2.toPath(), os);
            os.close();
        }
    }

}
