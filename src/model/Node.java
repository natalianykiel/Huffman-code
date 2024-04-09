package model;

public class Node implements Comparable<Node> {
    char znak;
    int freq;
    Node left;
    Node right;

    public Node(int freq, char znak) {
        this.freq = freq;
        this.znak = znak;
        this.left = null;
        this.right = null;
    }
    public Node(int freq, Node left, Node right) {
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    //Gettery//
    public char getZnak() {return znak;}
    public int getFreq() {return freq;}
    public Node getLeft() {return left;}
    public Node getRight() {return right;}

    @Override
    public int compareTo(Node o) {
        return this.freq - o.freq;
    }
    public boolean isLeaf() {
        return left == null && right == null;
    }

}
