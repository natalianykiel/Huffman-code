package model;

public class HuffmanTree {
    private Node root;
    private String[] codes;
    private PriorityQueue priority;

    public HuffmanTree(int[] freqs) {
        codes=new String[freqs.length];
        priority = new PriorityQueue();

        buildTree(freqs);
        codes(root, "");
    }

    private void buildTree(int[] frequencies) {
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                priority.add(new Node(frequencies[i], (char)i));
                //System.out.println((char) i +"->"+ frequencies[i]);
            }
        }
        while (priority.size() > 1) {
            Node left = priority.remove();
            Node right = priority.remove();
            Node parent = new Node(left.getFreq() + right.getFreq(), left, right);
            priority.add(parent);
        }
        this.root = priority.remove();
    }

    private void codes(Node root, String str) {
        if (root == null) {
            return;
        }
        if (root.isLeaf() ) {
            codes[root.getZnak()] = (char)root.getZnak() +"="+ str;
        } else {
            codes(root.getLeft(), str + "0");
            codes(root.getRight(), str + "1");
        }
    }

    public char getChar(String code) {
        Node currentNode = root;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '0') {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        return (char)currentNode.getZnak();
    }


    public boolean isCode(String code) {
        return isCode(this.root, code);
    }

    private boolean isCode(Node current, String code) {
        if (code.length() == 0 && current.isLeaf()) {
            return true;
        }
        if (code.length() == 0 && !current.isLeaf()) {
            return false;
        }
        if (code.charAt(0) == '0') {
            return isCode(current.left, code.substring(1));
        } else {
            return isCode(current.right, code.substring(1));
        }
    }

    public Node getRoot() {
        return root;
    }
    public void setRoot(Node root) {
        this.root = root;
    }
    public String[] getCodes() {
        return codes;
    }
    public void setCodes(String[] codes) {
        this.codes = codes;
    }

}
