import java.util.*;
class TreeNode<T extends Comparable<T>> {
    T data;
    TreeNode<T> left, right;
    public TreeNode(T data) {
        this.data = data;
        this.left = this.right = null;
    }
}
class BinaryTree<T extends Comparable<T>> {
    private TreeNode<T> root;

    public BinaryTree() {
        this.root = null;
    }
    public void insert(T value) {
        root = insertRec(root, value);
    }
    private TreeNode<T> insertRec(TreeNode<T> node, T value) {
        if (node == null) {
            return new TreeNode<>(value);
        }
        if (value.compareTo(node.data) < 0) {
            node.left = insertRec(node.left, value);
        } else {
            node.right = insertRec(node.right, value);
        }
        return node;
    }
    public void inOrder() {
        inOrderRec(root);
        System.out.println();
    }
    private void inOrderRec(TreeNode<T> node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.print(node.data + " ");
            inOrderRec(node.right);
        }
    }
    public void preOrder() {
        preOrderRec(root);
        System.out.println();
    }
    private void preOrderRec(TreeNode<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }
    public void postOrder() {
        postOrderRec(root);
        System.out.println();
    }
    private void postOrderRec(TreeNode<T> node) {
        if (node != null) {
            postOrderRec(node.left);
            postOrderRec(node.right);
            System.out.print(node.data + " ");
        }
    }
    public void levelOrder() {
        if (root == null) return;
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
            System.out.print(node.data + " ");
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        System.out.println();
    }
}
public class Program9 {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        System.out.print("Level Order: ");
        tree.levelOrder();
        System.out.print("In-order: ");
        tree.inOrder();
        System.out.print("Pre-order: ");
        tree.preOrder();
        System.out.print("Post-order: ");
        tree.postOrder();
    }
}