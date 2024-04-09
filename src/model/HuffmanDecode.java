package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class HuffmanDecode {
    private long fileSize;
    private HuffmanTree decodeTree;
    private int[] frequencies;

    public HuffmanDecode(String input) throws IOException {
        readHeader(input);
        decodeTree= new HuffmanTree(frequencies);
    }

    private String readLine(InputStream input) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = input.read()) != -1 && c != '\n') {
            sb.append((char) c);
        }
        return c == -1 && sb.length() == 0 ? null : sb.toString();
    }

    private void readHeader(String inputFile) throws IOException {
        try {
            InputStream input = new BufferedInputStream(new FileInputStream(inputFile));

            setFileSize(Long.parseLong(readLine(input)));
            int codesNum= Integer.parseInt(readLine(input));
            frequencies= new int[codesNum];
            for(int i=0;i<codesNum;i++){
                String code= readLine(input);
                String[] parts = code.split("=");
                frequencies[i]=Integer.parseInt(parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StringBuilder encodedData(String inputFile, String outputFilename) throws IOException {

        try (InputStream input = new BufferedInputStream(new FileInputStream(inputFile))) {
            readHeader(inputFile);

            byte[] inputData = new byte[(int) new File(inputFile).length()];
            input.read(inputData);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < inputData.length; i++) {
                for (int j = 7; j >= 0; j--) {
                    sb.append((inputData[i] & (1 << j)) != 0 ? '1' : '0');
                }
            }
            return sb;
        }
    }

    public File decompress(String inputFile, String outputFilename) throws IOException {

        StringBuilder sb = encodedData(inputFile, outputFilename);


        String decodedData = "";
        String prefix = "";


        for (int i = 0; i < sb.length(); i++) {
            prefix += sb.charAt(i);

            if (decodeTree.isCode(prefix)) {

                decodedData += decodeTree.getChar(prefix);
                prefix = "";
            }
        }


        File outputFile = new File(outputFilename);
        try (OutputStream output = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            output.write(decodedData.toString().getBytes());
        }
        return outputFile;
    }

    public long getFileSize() {

        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public HuffmanTree getDecodeTree() {
        return decodeTree;
    }

    public void setDecodeTree(HuffmanTree decodeTree) {
        this.decodeTree = decodeTree;
    }

    public int[] getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(int[] frequencies) {
        this.frequencies = frequencies;
    }
}