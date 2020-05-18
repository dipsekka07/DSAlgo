import java.lang.*;
import java.util.*;
import java.io.*;

public class buildCircularDLL {

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) {
        val = x;
        left = right = null;
    }
}
//----------------------------------------------
TreeNode solve (TreeNode root) {
    TreeNode head = buildDLL(root);
    TreeNode temp = head;
    while(temp != null && temp.right != null){
        temp = temp.right;
    }
    if(temp != null){
        temp.right = head;
        head.left = temp;
    }
    return head;
}

TreeNode buildDLL(TreeNode root) {
    if(root == null)
        return null;
    TreeNode left = buildDLL(root.left);
    TreeNode right = buildDLL(root.right);

    System.out.print(root.val + " ");
        
        TreeNode temp1 = left;
        while(temp1 != null && temp1.right != null){
            temp1 = temp1.right;
        }
        
        TreeNode temp2 = right;
        
        if(temp1 != null)
            temp1.right = root;
        root.left = temp1;
        if(temp2 != null)
            temp2.left = root;
        root.right = temp2;
        
        return left == null ? root : left;
}

void printDLL(TreeNode root){
    TreeNode temp = root;
    temp = temp!= null ? temp.right: null;
    while(temp != root){
        System.out.print(temp.val + " ");
        temp = temp.right;
    }
    System.out.println();
    
}


//------------------------------------------------
    BufferedReader br;
    PrintWriter out;
    
    void traversal(TreeNode root) {
        if(root == null)    return;
        TreeNode t = root;
        do {
            out.print(root.val +" ");
            root = root.right;
        } while(t != root && root != null);
        out.println();
    }
    
    void deSerialize(TreeNode root, int a[], int n) {
        if(n == 0)     return;
        int val = a[n-1];
        n--;
        if (val == -1) return;
        root = new TreeNode(val);
        deSerialize(root.left, a, n);
        deSerialize(root.right, a, n);
    }
    
    void solve() {
        int t = ni();
        while(t-- > 0) {
            int n = ni();
            int a[] = new int[n];
            for(int i = 0; i < n; i++)  a[n-i-1] = ni();
            TreeNode root = null;
            deSerialize(root, a, n);
            root = solve(root);
            traversal(root);
        }
    }

    int ni() {
        return Integer.parseInt(ns());
    }
    
    StringTokenizer ip;
    
    String ns() {
        if(ip == null || !ip.hasMoreTokens()) {
            try {
                ip = new StringTokenizer(br.readLine());
            } catch(IOException e) {
                throw new InputMismatchException();
            }
        }
        return ip.nextToken();
    }
    
    void run() {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        out.flush();
    }
    
    public static void main(String[] args) {
        new buildCircularDLL().run();
    }

}