package Model;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {


    public static void main( String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("\t  MENU OPTIONS  \t");
        System.out.println("-----------------------------------");
        System.out.println("1- Encode");
        System.out.println("2- Decode");
        System.out.println("-----------------------------------");
        int op = input.nextInt();
        if(op==1)
            HuffmanEncode.encode(input);
        else if(op ==2)
            HuffmanDecode.decode(input);
        else
            System.out.println("INVALID INPUT");

    }
}
