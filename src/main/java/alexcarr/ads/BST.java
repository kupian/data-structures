package alexcarr.ads;


public class BST {
    private Node root;

    private class Node {
        private final int key;
        private Node left, right, p;
        private int size;

        public Node(int key, int size) {
            this.key = key;
            this.size = size;
            this.left = null;
            this.right = null;
            this.p = null;
        }
    }

    public BST(Node root) {
        this.root = root;
    }

    public static Node max(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public static Node min(Node x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public Node max() {
        return max(root);
    }

    public Node min() {
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

    public static Node successor(Node x) {
        if(x == null) return null;
        if (x.right != null) return min(x.right);

        Node y = x.p;
        while (y != null && x == y.right) {
            x = y;
            y = y.p;
        }
        return y;
    }

    public static Node predecessor(Node x) {
        if(x == null) return null;
        if (x.left != null) return max(x.left);
        Node y = x.p;
        while (y != null && x == y.left) {
            x = y;
            y = y.p;
        }
        return y;
    }

    public static int size(Node x) {
        if(x == null) return 0;
        return size(x.left) + size(x.right) + 1;
    }

    public static int height(Node x) {
        if(x == null) return 0;
        if(x.left == null && x.right == null) return 0;
        return Math.max(height(x.left), height(x.right)) + 1;
    }

    public static void insert(BST T, Node z) {
        Node x = T.root;
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
            T.root = z;
        }
        else if (z.key < y.key) {
            y.left = z;
        }
        else {
            y.right = z;
        }
    }

    public static void transplant(BST T, Node u, Node v) {
        if (u.p == null) T.root = v;
        else if (u == u.p.left) u.p.left = v;
        else u.p.right = v;

        if(v != null) v.p = u.p;
    }

    public static void delete(BST T, Node z) {
        if (z.left == null) transplant(T, z, z.right);
        else if (z.right == null) transplant(T, z, z.left);
        else {
            Node y = min(z.right);
            if (y.p != z) {
                transplant(T, z, y.right);
                y.right = z.right;
                y.right.p = y;
            }
            transplant(T, z, y);
            y.left = z.left;
            y.left.p = y;
        }
    }

    public static void inorder(Node T) {
        if (T == null) return;
        inorder(T.left);
        System.out.println(T.key);
        inorder(T.right);
    }

    public static void preorder(Node T) {
        if (T == null) return;
        System.out.println(T.key);
        preorder(T.left);
        preorder(T.right);
    }

    public static void postorder(Node T) {
        if (T == null) return;
        postorder(T.left);
        postorder(T.right);
        System.out.println(T.key);
    }

    public boolean checkBST() {
        return checkBST(root);
    }

    public static boolean checkBST(Node T) {
        return inRangeBST(T, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean inRangeBST(Node T, int low, int high) {
        if (T == null) return true;

        if (T.key < low || T.key > high) return false;

        return inRangeBST(T.left, low, T.key) && inRangeBST(T.right, T.key, high);
    }
}
