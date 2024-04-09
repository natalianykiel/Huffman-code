package org.example;
import java.io.*;
import java.net.Socket;

public class Launcher {
    public Launcher() {
        try {
            initiateConnection();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void initiateConnection() throws IOException {
        Socket socket = new Socket("127.0.0.1", 6700);
        InputStream is = socket.getInputStream();
        OutputStream os1 = new FileOutputStream("src/main/java/org/example/end.table.txt");
        OutputStream os2 = new FileOutputStream("src/main/java/org/example/end.Huffman.txt");

        byte[] buffer = new byte[1024];
        int bytesRead;
        OutputStream currentOs = os1;
        while ((bytesRead = is.read(buffer)) != -1) {
            String str = new String(buffer, 0, bytesRead);
            if (str.contains("ZMIANA PLIKU\n")) {
                String[] parts = str.split("ZMIANA PLIKU\n", 2);
                currentOs.write(parts[0].getBytes());
                currentOs = os2;
                currentOs.write(parts[1].getBytes());
            } else {
                currentOs.write(buffer, 0, bytesRead);
            }
        }

        is.close();
        os1.close();
        os2.close();
    }
}
