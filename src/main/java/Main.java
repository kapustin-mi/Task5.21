public class Main {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.add(8);
        tree.add(9);
        tree.add(5);
        System.out.println(tree.findSubtrees());
    }
}
