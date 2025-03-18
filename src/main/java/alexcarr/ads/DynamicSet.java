package alexcarr.ads;

public class DynamicSet {
    private Node root = null;

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

        private Node min() {
            if (left == null) {
                return this;
            }
            return left.min();
        }
    }

    public static void add(DynamicSet S, int x) {
        if (isElement(S, x)) return;

        Node node = S.new Node(x, 1);
        Node z = S.root;
        Node y = null;
        while (z != null) {
            y = z;
            if (node.key < z.key) {
                z = z.left;
            }
            else {
                z = z.right;
            }
        }
        node.p = y;
        if (y == null) {
            S.root = node;
        }
        else if (node.key < y.key) {
            y.left = node;
        }
        else {
            y.right = node;
        }
    }

    private void transplant(Node u, Node v) {
        if (u.p == null) root = v;
        else if (u == u.p.left) u.p.left = v;
        else u.p.right = v;

        if(v != null) v.p = u.p;
    }

    private Node searchNode(Node x, int key) {
        if(x == null) return null;
        if(x.key < key) return searchNode(x.right, key);
        if(x.key > key) return searchNode(x.left, key);

        return x;
    }

    public static boolean isElement(DynamicSet S, int key) {
        return S.searchNode(S.root, key) != null;
    }

    public static boolean setEmpty(DynamicSet S) {
        return S.root == null;
    }

    private int nodeSize(Node x) {
        if(x == null) return 0;

        int left = nodeSize(x.left);
        int right = nodeSize(x.right);
        return left + right + 1;
    }

    public static int setSize(DynamicSet S) {
        return S.nodeSize(S.root);
    }

    public static void delete(DynamicSet S, int x) {
        if (!isElement(S,x)) return;

        Node node = S.searchNode(S.root, x);

        if (node.left == null) S.transplant(node, node.right);
        else if (node.right == null) S.transplant(node, node.left);
        else {
            Node y = node.right.min();
            if (y.p != node) {
                S.transplant(node, y.right);
                y.right = node.right;
                y.right.p = y;
            }
            S.transplant(node, y);
            y.left = node.left;
            y.left.p = y;
        }
    }

    public static void main(String[] args) {
        DynamicSet S = new DynamicSet();
        add(S, 5);
        add(S, 3);
        add(S, 2);
        add(S, 1);
        add(S, 12);
        add(S,14);
        add(S,14);

        System.out.println(isElement(S, 12));
        delete(S, 12);
        System.out.println(isElement(S, 12));

        System.out.println(setEmpty(S));
        System.out.println(setSize(S));
    }
}
