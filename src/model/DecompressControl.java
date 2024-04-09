package model;
import java.io.File;
import java.io.IOException;

public class DecompressControl {
    private File file;

    public DecompressControl(File file) {
        this.file = file;
        decompress();
    }

    private void decompress() {
        try {
            HuffmanDecode decoder = new HuffmanDecode(file.getPath());
            decoder.decompress(file.getPath(), "C:/Users/nnyki/IdeaProjects/Huffman/src/b.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
