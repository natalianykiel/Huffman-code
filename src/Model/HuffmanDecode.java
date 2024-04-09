package Model;

import java.io.*;
import java.util.Scanner;
import Model.HuffmanTree;

public class HuffmanDecode {
    static FileReader reader = null;
    public static void decode(Scanner input){
        System.out.println("Enter the path and name of your file without '.txt' prefix, example : Desktop\\Filename");
        input.nextLine();
        String file = "C:\\Users\\nnyki\\Desktop\\dane";
        String code ="";
        int i=0;
        String temp,st;
        String text="";
        try{
            FileInputStream is =new FileInputStream(file+".Huffman");
            byte[] b = is.readAllBytes();
            is.close();

            for(i=0 ; i<b.length-2 ; i++){
                temp = String.format("%7s", Integer.toBinaryString(b[i] & 0xFF)).replace(' ', '0');
                code = code + temp;
            }
            int len = b[b.length -1];
            temp = String.format("%s", Integer.toBinaryString(b[b.length-2]).replace(' ', '0'));
            for(i=0 ; i< len-temp.length() ; i++)
                code=code+"0";
            code = code + temp;
            //  System.out.println(code);
        }catch(IOException e) {
            System.out.println("File Not Found!");
            e.printStackTrace();
        }
        char ch;
        Node root = new Node('*',null,null);
        try{                                 //reading File.table and recreating Huffman Tree
            reader =new FileReader(file+".table");
            Scanner in = new Scanner(reader);
            while(in.hasNext()) {
                st =in.nextLine();
                ch = st.charAt(0);
                temp= st.substring(2,st.length());
                //  System.out.print(temp);
                HuffmanTree.create_Tree(root, temp, ch);
            }
            reader.close();
            in.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
        Node t=root ;
        for(i=0 ; i< code.length(); i++){    //decoding using huffman tree
            if(t== null)
                break;

            if(code.charAt(i)=='0'){
                t=t.left;
            }
            else if(code.charAt(i)=='1'){
                t=t.right;
            }
            if(t.left == null && t.right == null && t.znak !='*'){
                text = text + t.znak ;
                t=root;
            }
        }
        System.out.println(text);
    }
}
