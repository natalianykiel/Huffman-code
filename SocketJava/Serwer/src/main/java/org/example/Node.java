package org.example;

class Node{
    char znak;
    int frequency;
    Node left;
    Node right;
    Node(char znak , int frequency , Node left, Node right){
        this.znak = znak;
        this.frequency=frequency;
        this.left = left;
        this.right = right;
    }
    Node(char znak  , Node left, Node right){
        this.znak = znak;
        this.left = left;
        this.right = right;
    }
}
