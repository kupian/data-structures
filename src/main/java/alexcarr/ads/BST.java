package alexcarr.ads;


import java.util.NoSuchElementException;

public class BST {
    private Node root;

    private class Node {
        private int key;
        private Node left, right, p;
        private int size;

        public Node(int key, int size) {
            this.size = size;
            this.key = key;
            this.left = null;
            this.right = null;
            this.p = null;
        }
    }

    public BST() {
        root = null;
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private Node max(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    private Node min(Node x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public Node max() {
        if (isEmpty()) throw new NoSuchElementException("Tree is empty");
        return max(root);
    }

    public Node min() {
        if (isEmpty()) throw new NoSuchElementException("Tree is empty");
        return min(root);
    }

    public static Node search(Node x, int key) {
        if(x == null) return null;
        if(x.key < key) return search(x.right, key);
        if(x.key > key) return search(x.left, key);

        return x;
    }

    public Node search(int key) {
        return search(root, key);
    }

    public Node successor(Node x) {
        if(x == null) return null;
        if (x.right != null) return min(x.right);

        Node y = x.p;
        while (y != null && x == y.right) {
            x = y;
            y = y.p;
        }
        return y;
    }

    public Node predecessor(Node x) {
        if (x == null) return null;
        if (x.left != null) return max(x.left);
        Node y = x.p;
        while (y != null && x == y.left) {
            x = y;
            y = y.p;
        }
        return y;
    }

    public int height(Node x) {
        if(x == null) return 0;
        if(x.left == null && x.right == null) return 0;
        return Math.max(height(x.left), height(x.right)) + 1;
    }

    public void insert(Node z) {
        Node x = root;
        Node y = null;
        while (x != null) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            }
            else {
                x = x.right;
            }
        }
        z.p = y;
        if (y == null) {
            root = z;
        }
        else if (z.key < y.key) {
            y.left = z;
        }
        else {
            y.right = z;
        }
    }

    public void transplant(Node u, Node v) {
        if (u.p == null) root = v;
        else if (u == u.p.left) u.p.left = v;
        else u.p.right = v;

        if(v != null) v.p = u.p;
    }

    public void delete(Node z) {
        if (z.left == null) transplant(z, z.right);
        else if (z.right == null) transplant(z, z.left);
        else {
            Node y = min(z.right);
            if (y.p != z) {
                transplant(z, y.right);
                y.right = z.right;
                y.right.p = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.p = y;
        }
    }

    public void inorder(Node T) {
        if (T == null) return;
        inorder(T.left);
        System.out.println(T.key);
        inorder(T.right);
    }

    public void preorder(Node T) {
        if (T == null) return;
        System.out.println(T.key);
        preorder(T.left);
        preorder(T.right);
    }

    public void postorder(Node T) {
        if (T == null) return;
        postorder(T.left);
        postorder(T.right);
        System.out.println(T.key);
    }

    public boolean checkBST() {
        return checkBST(root);
    }

    public boolean checkBST(Node T) {
        return inRangeBST(T, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean inRangeBST(Node T, int low, int high) {
        if (T == null) return true;

        if (T.key < low || T.key > high) return false;

        return inRangeBST(T.left, low, T.key) && inRangeBST(T.right, T.key, high);
    }
}
