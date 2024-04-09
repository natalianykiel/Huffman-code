package org.example;

import java.util.Comparator;

public class CompareNodes implements Comparator<Node> {
    public int compare(Node x , Node y){               //comparing Nodes on the basis of data values of the nodes.
        return x.frequency - y.frequency;
    }

}