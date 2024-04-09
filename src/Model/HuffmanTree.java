package Model;

public class HuffmanTree {
    public static void create_Tree(Node root , String str ,char ch){
        if(str.length()<=1){
            if(str.charAt(0)=='0'){
                root.left = new Node(ch,null,null );
            }
            else{
                root.right = new Node(ch,null,null );
            }
            return ;
        }

        if(str.charAt(0)== '0'){
            if(root.left==null)
                root.left = new Node('*',null,null );
            create_Tree(root.left, str.substring(1), ch);
        }
        else if(str.charAt(0)=='1'){
            if(root.right==null)
                root.right = new Node('*',null,null );
            create_Tree(root.right, str.substring(1), ch);
        }
        return ;
    }
}
