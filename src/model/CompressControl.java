package model;
import model.HuffmanEncode;
import java.io.File;
import java.io.IOException;

public class CompressControl {
    private File file;

    public CompressControl(File file) {
        this.file = file;
        compress();
    }

    private void compress() {
        int[] frequencies;
        try {
            frequencies = readFrequencies(file);
            HuffmanTree tree = new HuffmanTree(frequencies);
            HuffmanEncode encoder = new HuffmanEncode(tree.getCodes());
            encoder.compress(file.getAbsolutePath(), "C:/Users/nnyki/IdeaProjects/Huffman/src/b.txt", tree.getCodes(), frequencies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[] readFrequencies(File file) throws IOException {
        // Implement logic to read frequencies from the file
        return new int[256];
    }
}
