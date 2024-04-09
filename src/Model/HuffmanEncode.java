package Model;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanEncode {

    static File f = null;
    static FileWriter myWriter =null;
    static FileReader reader = null;


    public static void encode(Scanner input){
        System.out.println("Enter the path and name of your file without '.txt' prefix, example : Desktop\\Filename");
        input.nextLine();
        String file = "C:\\Users\\nnyki\\Desktop\\dane";
        String text="";
        int n =63,i;
        char[] tabZnak = new char[n];
        int[] frequencyTab = new int[n];
        String[] codesHuffman = new String[n];
        String code ="";

        for(i=0 ; i<26 ; i++){
            tabZnak[i] =(char)( 97 + i);
        }
        for(i=0 ; i<26 ; i++){
            tabZnak[i+26] =(char)( 65 + i);
        }
        for(i=0 ; i<10 ; i++){
            tabZnak[i+52] =(char)( 48 + i);
        }
            tabZnak[62] = (char)(32);

        //inicjalizacja frequency array
        for(i=0 ; i<n ; i++){
            frequencyTab[i]=0;
        }
        try{
            reader =new FileReader(file+".txt");
            Scanner in = new Scanner(reader);
            while(in.hasNext()) {
                text=text+in.nextLine();
            }
            reader.close();
            in.close();
        }catch(IOException e) {
            e.printStackTrace();
        }


        //aktualizacja tab frequency + tworzenie kolejki
        for(i=0 ; i<text.length() ; i++){  // fr[i] means how many times ch[i] has been repeated in the text file
            frequencyTab[new String(tabZnak).indexOf(text.charAt(i))]++;
        }

        PriorityQueue<Node> q = new PriorityQueue<Node>(n,new CompareNodes());  //creating a min-priority queue

        for(i=0 ; i<n ; i++){  // add the letters as leaves to q
            if(frequencyTab[i] !=0 ){
                Node h = new Node(tabZnak[i],frequencyTab[i],null,null);

                q.add(h);
            }
        }
        Node root = q.peek(); //root of huffman tree
        while(q.size() > 1){  // creating huffman tree
            Node left = q.peek();
            q.poll();
            Node right = q.peek();
            q.poll();
            Node z = new Node('*',left.frequency +right.frequency , left , right);
            q.add(z);
            root = z;
        }
        try {   // traversing the tree and storing it's data  in "file.table"
            f = new File(file+".table");
            myWriter = new FileWriter(file+".table");
            if(root.left==null && root.right == null)
                printCode(root, "1" ,codesHuffman ,tabZnak);
            else
                printCode(root, "" ,codesHuffman,tabZnak);
            myWriter.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //kodujemy i przekształcamy zakodowe w ciag bajtów
        for(i=0 ; i<text.length() ; i++){
            code = code + codesHuffman[new String(tabZnak).indexOf(text.charAt(i))];
        }
        byte[] b;
        if(code.length()%7 == 0 || code.length()<7)
            b = new byte[code.length()/7 +1];
        else
            b = new byte[code.length()/7 +2];
        //tu jest to dzielenie na byte
        int k=0;
        String temp ="";
        int len=7 ;
        for(i=0 ; i<=code.length()/7;i++){
            if((7*i)>=code.length())
                break;
            temp = "";
            if(7*(i+1) < code.length()){
                temp =temp + code.substring(i*7, (i+1)*7);
            }
            else{
                temp = temp+ code.substring(i*7);
                len = temp.length();
            }

            b[k++] = Byte.parseByte(temp,2);

        }
        //konwersja spowrotem
        String l = len + "";
        b[k] = Byte.parseByte(l,10);
        try {
            f = new File(file+".Huffman");
            FileOutputStream fos = new FileOutputStream(file+".Huffman") ;  //Storing b[] in File.huffman
            fos.write(b);
            fos.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

    }

    public static void printCode(Node root, String s ,String[] str, char[] ch)
    {

        if (root.left == null && root.right == null && root.znak != '*') {

            str[new String(ch).indexOf(root.znak)] = s;
            try {
                myWriter.write(root.znak+" "+s+"\n");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            return;
        }

        printCode(root.left, s + "0" ,str ,ch);
        printCode(root.right, s + "1" , str , ch);

    }
}
