import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BinaryTree {
    private TreeNode root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(int[] treeNumbers) {
        for (int number : treeNumbers) {
            add(number);
        }
    }

    public void add(int value) {
        root = addNode(root, value);
    }

    private TreeNode addNode(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }

        if (value < node.getValue()) {
            node.setLeft(addNode(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(addNode(node.getRight(), value));
        }

        return node;
    }

    public void delete(int value) {
        TreeNode node = root;
        TreeNode parentNode = root;
        boolean isLeftChild = true;

        while (node.getValue() != value) {
            parentNode = node;
            if (value < node.getValue()) {
                isLeftChild = true;
                node = node.getLeft();
            } else {
                isLeftChild = false;
                node = node.getRight();
            }
            if (node == null) {
                return;
            }
        }

        if (node.getLeft() == null && node.getRight() == null) {
            if (node == root) {
                root = null;
            } else if (isLeftChild) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }
        } else if (node.getRight() == null) {
            if (node == root) {
                root = node.getLeft();
            } else if (isLeftChild) {
                parentNode.setLeft(node.getLeft());
            } else {
                parentNode.setRight(node.getLeft());
            }
        } else if (node.getLeft() == null) {
            if (node == root) {
                root = node.getRight();
            } else if (isLeftChild) {
                parentNode.setLeft(node.getRight());
            } else {
                parentNode.setRight(node.getRight());
            }
        } else {
            TreeNode heir = findSmallestValue(node.getRight());
            delete(heir.getValue());

            heir.setLeft(node.getLeft());
            heir.setRight(node.getRight());
            if (node == root) {
                root = heir;
            } else if (isLeftChild) {
                parentNode.setLeft(heir);
            } else {
                parentNode.setRight(heir);
            }
        }
    }

    private TreeNode findSmallestValue(TreeNode node) {
        TreeNode parentNode = node;
        TreeNode currentNode = node;

        while (currentNode != null) {
            parentNode = currentNode;
            currentNode = currentNode.getLeft();
        }

        return parentNode;
    }

    public List<String> findSubtrees() {
        if (root != null) {
            if (root.getRight() != null || root.getLeft() != null) {
                List<String> ways = new ArrayList<>();
                HashMap<String, Integer> allSums = findAllSums();
                Object[] keys = allSums.keySet().toArray();
                int maxSum = allSums.get(keys[0]);

                for (Object key : keys) {
                    if (allSums.get(key) > maxSum) {
                        maxSum = allSums.get(key);
                        ways.clear();
                        ways.add((String) key);
                    } else if (allSums.get(key) == maxSum) {
                        ways.add((String) key);
                    }
                }
                return ways;
            }
        }

        return null;
    }

    private HashMap<String, Integer> findAllSums() {
        HashMap<String, Integer> sums = new HashMap<>();
        StringBuilder way = new StringBuilder();

        findChildesSum(root, sums, way);
        return sums;
    }

    private void findChildesSum(TreeNode node, HashMap<String, Integer> sums, StringBuilder way) {
        if (node != null) {
            way.append("L");
            if (node.getLeft() != null) {
                sums.put(way.toString(), findSubtreeSum(node.getLeft()));
                findChildesSum(node.getLeft(), sums, way);
            }

            way.setLength(way.length() - 1);
            way.append("R");
            if (node.getRight() != null) {
                sums.put(way.toString(), findSubtreeSum(node.getRight()));
                findChildesSum(node.getRight(), sums, way);
            }
            way.setLength(way.length() - 1);
        }
    }

    private int findSubtreeSum(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return node.getValue() + findSubtreeSum(node.getRight()) + findSubtreeSum(node.getLeft());
        }
    }

    private static class TreeNode {
        private int value;
        private TreeNode leftChild;
        private TreeNode rightChild;

        TreeNode(int value, TreeNode leftChild, TreeNode rightChild) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        TreeNode(int value) {
            this(value, null, null);
        }

        int getValue() {
            return value;
        }

        void setValue(int value) {
            this.value = value;
        }

        TreeNode getLeft() {
            return leftChild;
        }

        void setLeft(TreeNode leftChild) {
            this.leftChild = leftChild;
        }

        void setRight(TreeNode rightChild) {
            this.rightChild = rightChild;
        }

        TreeNode getRight() {
            return rightChild;
        }

    }

    public String toString() {
        List<TreeNode> childes = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int height = 0;

        if (root != null) {
            sb.append(root.getValue());
            sb.append("\n");
        } else {
            return null;
        }

        childes.add(root.getRight());
        childes.add(root.getLeft());
        addChildes(sb, childes, height + 1);

        return sb.toString();
    }

    private void addChildes(StringBuilder sb, List<TreeNode> roots, int height) {
        List<TreeNode> childes = new ArrayList<>();
        for (TreeNode root : roots) {
            if (root != null) {
                sb.append("\t".repeat(height));
                sb.append(root.getValue());
                sb.append("\n");

                childes.add(root.getRight());
                childes.add(root.getLeft());
                addChildes(sb, childes, height + 1);

                childes.clear();
            }
        }
    }
}
